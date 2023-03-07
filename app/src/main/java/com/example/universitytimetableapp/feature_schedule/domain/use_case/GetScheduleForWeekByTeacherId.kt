package com.example.universitytimetableapp.feature_schedule.domain.use_case

import android.util.Log
import com.example.universitytimetableapp.common.Resource
import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleItem
import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleItemsForDay
import com.example.universitytimetableapp.feature_schedule.domain.repository.ScheduleFeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetScheduleForWeekByTeacherId @Inject constructor(
    private val repository: ScheduleFeatureRepository
) {

    operator fun invoke(
        id: String,
        startDate: String,
        endDate: String
    ): Flow<Resource<List<ScheduleItemsForDay>>> =
        flow {
            try {
                emit(Resource.Loading<List<ScheduleItemsForDay>>())
                val listOfScheduleForDay =
                    repository.getScheduleForWeekByTeacherId(id, startDate, endDate)
                val listOfLessonTime = repository.getLessonsTimeDetail()

                val listOfScheduleItemForWeek = mutableListOf<ScheduleItemsForDay>()

                for (scheduleForDay in listOfScheduleForDay) {
                    var previousLessonNumber = 0
                    val listOfScheduleItemCard = mutableListOf<ScheduleItem>()
                    for (lessonIndex in 0..scheduleForDay.lessons.size - 2) {
                        val lessonNumberForCurrentLesson =
                            scheduleForDay.lessons[lessonIndex].lessonTime.lessonNumber
                        val lessonNumberForNextLesson =
                            scheduleForDay.lessons[lessonIndex + 1].lessonTime.lessonNumber

                        if (lessonNumberForCurrentLesson > previousLessonNumber) {
                            listOfScheduleItemCard.add(
                                ScheduleItem.WindowItem(
                                    windowNumber = (lessonNumberForCurrentLesson - previousLessonNumber).toString(),
                                    startTime = listOfLessonTime[previousLessonNumber].startTime,
                                    endTime = listOfLessonTime[lessonNumberForCurrentLesson - 1].endTime
                                )
                            )
                        }
                        listOfScheduleItemCard.add(
                            ScheduleItem.SubjectItem(
                                lesson = scheduleForDay.lessons[lessonIndex]
                            )
                        )
                        Log.i(
                            "lessonNumberForCurrentLesson",
                            lessonNumberForCurrentLesson.toString()
                        )
                        Log.i("lessonNumberForNextLesson", lessonNumberForNextLesson.toString())
                        Log.i(
                            "difference",
                            (lessonNumberForNextLesson - lessonNumberForCurrentLesson).toString()
                        )
                        if (lessonNumberForNextLesson - lessonNumberForCurrentLesson == 1) {
                            listOfScheduleItemCard.add(
                                ScheduleItem.BreakItem(
                                    startTime = scheduleForDay.lessons[lessonIndex].lessonTime.endTime,
                                    endTime = scheduleForDay.lessons[lessonIndex + 1].lessonTime.startTime
                                )
                            )
                            if (lessonIndex == scheduleForDay.lessons.size - 2 && lessonNumberForNextLesson - lessonNumberForCurrentLesson == 1) {
                                listOfScheduleItemCard.add(
                                    ScheduleItem.SubjectItem(
                                        lesson = scheduleForDay.lessons[lessonIndex + 1]
                                    )
                                )
                            }
                        }



                        previousLessonNumber = lessonNumberForCurrentLesson + 1
                    }
                    listOfScheduleItemForWeek.add(
                        ScheduleItemsForDay(
                            listOfScheduleItemCard = listOfScheduleItemCard
                        )
                    )
                }
                emit(Resource.Success<List<ScheduleItemsForDay>>(listOfScheduleItemForWeek))

            } catch (e: HttpException) {
                emit(
                    Resource.Error<List<ScheduleItemsForDay>>(
                        e.localizedMessage ?: "An unexpected error occured"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error<List<ScheduleItemsForDay>>("Could not reach server. Check your internet connection"))
            }

        }
}