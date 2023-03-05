package com.example.universitytimetableapp.feature_application_login.presentation.choice

import androidx.lifecycle.*
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_application_login.domain.model.GroupsTeachersItem
import com.example.universitytimetableapp.feature_application_login.domain.model.UserSettings
import com.example.universitytimetableapp.feature_application_login.domain.use_case.GetSelectionListUseCase
import com.example.universitytimetableapp.feature_application_login.domain.use_case.PutUserSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ChoosingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSelectionListUseCase: GetSelectionListUseCase,
    private val putUserSettingsUseCase: PutUserSettingsUseCase
) : ViewModel() {

    val case: String
    val studentEmail: String

    private val _chosenRole = MutableLiveData("")
    val chosenRole: LiveData<String> = _chosenRole

    private val _uiState = MutableLiveData(ChoosingUiState())
    val uiState: LiveData<ChoosingUiState> = _uiState

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search
    fun setSearch(value: String) {
        _search.value = value
    }

    private val _choosingItem = MutableLiveData<GroupsTeachersItem>(null)
    val choosingItem: LiveData<GroupsTeachersItem> = _choosingItem
    fun setChoosingItem(value: Int) {
        _choosingItem.value = _listWithFilter.value[value]
    }

    private val _listWithFilter = MutableStateFlow(listOf<GroupsTeachersItem>())
    val listWithFilter = search
        .debounce(500)
        .combine(_listWithFilter) { text, list ->
            if (text.isEmpty()) {
                list
            }
            else {
                _choosingItem.value = null
                list.filter {
                    it.name.contains(text, true)
                }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _listWithFilter.value
        )

    init {
        case = checkNotNull(savedStateHandle[Constants.CASE])
        if (case == Constants.CHANGE_GROUP) {
            _uiState.value = ChoosingUiState(isRoleChosen = true)
            _chosenRole.value = Constants.STUDENT
            studentEmail = checkNotNull(savedStateHandle[Constants.EMAIL])
            getListItem(Constants.STUDENT)
        }
        else {
            studentEmail = ""
        }
    }

    fun choosingRole(role: String) {
        _uiState.value = _uiState.value!!.copy(isRoleChosen = true)
        _chosenRole.value = role
        _choosingItem.value = null
        getListItem(role)
    }

    fun deselecting() {
        if (case != Constants.CHANGE_GROUP) {
            _uiState.value = _uiState.value!!.copy(isRoleChosen = false)
        }
        else {
            _uiState.value = _uiState.value!!.copy(
                mayNavigate = true,
                destinationString = Screen.ProfileScreen.route
            )
        }
    }


    fun goToNextScreen() {
        if (_choosingItem.value == null) {
            return
        }
        if (_uiState.value!!.isShowDialog) {
            _uiState.value = _uiState.value!!.copy(
                isShowDialog = false,
                mayNavigate = true
            )
        }
        else if (case == Constants.CHANGE_GROUP) {
            _uiState.value = _uiState.value!!.copy(isShowDialog = true)
            return
        }
        else {
            _uiState.value = _uiState.value!!.copy(mayNavigate = true)
        }
        _uiState.value = _uiState.value!!.copy(destinationString = destinationFromCase())
        if (case != Constants.CHOOSE_SCHEDULE && case != Constants.REGISTER) {
            putUserSettingsUseCase(UserSettings(
                role = _chosenRole.value!!,
                idChosenItem = _choosingItem.value!!.id,
                nameChosenItem = _choosingItem.value!!.name
            ))
        }
    }

    fun setDefaultState() {
        _uiState.value = _uiState.value!!.copy(mayNavigate = false)
    }

    private fun destinationFromCase(): String {
        return when (case) {
            Constants.REGISTER -> {
                    "${Screen.RegistrationScreen.route}/${_chosenRole.value}/${_choosingItem.value!!.id}/${_choosingItem.value!!.name}"
            }
            Constants.CHANGE_GROUP -> Screen.ProfileScreen.route
            else -> {
                    "${Screen.ScheduleScreen.route}/${_chosenRole.value}/${_choosingItem.value!!.id}/${_choosingItem.value!!.name}"
            }
        }
    }

    private fun getListItem(role: String) {
        viewModelScope.launch {
            getSelectionListUseCase(role).collect { result ->
                result.onSuccess {
                    _listWithFilter.value = it
                }.onFailure {

                }
            }
        }
    }
}