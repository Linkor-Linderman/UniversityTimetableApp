package com.example.universitytimetableapp.feature_application_login.presentation.choice

import com.example.universitytimetableapp.feature_application_login.domain.model.GroupsTeachersItem

data class ChoosingUiState(
    val isLoading: Boolean = false,
    val isShowMessage: Boolean = false,
    val message: String = "",
    val isRoleChosen: Boolean = false,
    val itemList: List<GroupsTeachersItem> = listOf(GroupsTeachersItem("1","972101"), GroupsTeachersItem("2","972102"), GroupsTeachersItem("3","972103"),
        GroupsTeachersItem("4","972105"), GroupsTeachersItem("5","972110"), GroupsTeachersItem("6","972201"), GroupsTeachersItem("7","972202"),
        GroupsTeachersItem("8","972203"), GroupsTeachersItem("9","972205"), GroupsTeachersItem("10","972210")),
    val isShowDialog: Boolean = false,
    val mayNavigate: Boolean = false,
    val destinationString: String = ""
)
