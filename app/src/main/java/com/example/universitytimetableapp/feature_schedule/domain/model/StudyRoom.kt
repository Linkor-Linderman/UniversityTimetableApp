package com.example.universitytimetableapp.feature_schedule.domain.model

data class StudyRoom(
    val buildingNumber: Int,
    val floor: Int?,
    val name: String?,
    val number: String
) {
    fun getStudyRoomLocal(): String {
        val floor = if (floor != null) "(${floor})" else ""
        return  "$number $buildingNumber $floor ${name ?: ""}"
    }
}