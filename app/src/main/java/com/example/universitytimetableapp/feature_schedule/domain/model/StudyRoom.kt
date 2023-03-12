package com.example.universitytimetableapp.feature_schedule.domain.model

data class StudyRoom(
    val buildingNumber: Int,
    val floor: Int?,
    val name: String?,
    val number: String
) {
    fun getStudyRoomLocal(): String {
        val floorString = if (floor != null) ", $floor этаж" else ""
        return  "$number ($buildingNumber корпус$floorString) ${name?: ""}".trimEnd()
    }
}