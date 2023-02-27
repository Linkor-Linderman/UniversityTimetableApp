package com.example.universitytimetableapp.feature_application_login.presentation.choice

import androidx.lifecycle.*
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ChoosingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val case: String
    private val _chosenRole = MutableLiveData("")
    val chosenRole: LiveData<String> = _chosenRole

    private val _uiState = MutableLiveData(ChoosingUiState())
    val uiState: LiveData<ChoosingUiState> = _uiState

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search
    fun setSearch(value: String) {
        _search.value = value
    }

    private val _listWithFilter = MutableStateFlow(_uiState.value?.itemList ?: listOf())
    val listWithFilter = search
        .debounce(500)
        .combine(_listWithFilter) { text, list ->
            if (text.isEmpty()) {
                list
            }
            else {
                list.filter {
                    it.contains(text, true)
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
        }
    }

    fun choosingRole(role: String) {
        _uiState.value = _uiState.value!!.copy(isRoleChosen = true)
        _chosenRole.value = role
        if (role == Constants.STUDENT) {
            //fetchGroup
        }
        else {
            //fetchTeacher
        }
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
    }

    fun setDefaultState() {
        _uiState.value = _uiState.value!!.copy(mayNavigate = false)
    }

    private fun destinationFromCase(): String {
        return when (case) {
            Constants.REGISTER -> {
                if (_chosenRole.value == Constants.TEACHER)
                    "${Screen.RegistrationScreen.route}/${_chosenRole.value}/123id?${Constants.TEACHER_NAME}=Даммер Д Д"
                else
                    "${Screen.RegistrationScreen.route}/${_chosenRole.value}/123id"
            }
            Constants.CHANGE_GROUP -> Screen.ProfileScreen.route
            else -> Screen.ScheduleScreen.route
        }
    }
}