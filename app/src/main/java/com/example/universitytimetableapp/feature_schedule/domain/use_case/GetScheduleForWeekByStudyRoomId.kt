package com.example.universitytimetableapp.feature_schedule.domain.use_case

import com.example.universitytimetableapp.common.Resource
import com.example.universitytimetableapp.feature_schedule.data.repository.RepositoryForTest
import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleItem
import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleItemsForDay
import com.example.universitytimetableapp.feature_schedule.domain.repository.ScheduleFeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetScheduleForWeekByStudyRoomId @Inject constructor(
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
                val testRepository = RepositoryForTest()
                val listOfScheduleForDay =
                    testRepository.getScheduleForWeekByGroupId(id, startDate, endDate)
                val listOfLessonTime = repository.getLessonsTimeDetail()

                val listOfScheduleItemForWeek = mutableListOf<ScheduleItemsForDay>()

                for (scheduleForDay in listOfScheduleForDay) {
                    var previousLessonNumber = listOfLessonTime[0].lessonNumber
                    val listOfScheduleItemCard = mutableListOf<ScheduleItem>()
                    for (lessonIndex in 0 until scheduleForDay.lessons.size) {
                        val lessonNumberForCurrentLesson =
                            scheduleForDay.lessons[lessonIndex].lessonTime.lessonNumber
                        val lessonNumberForNextLesson =
                            if (lessonIndex + 1 < scheduleForDay.lessons.size)
                                scheduleForDay.lessons[lessonIndex + 1].lessonTime.lessonNumber
                            else
                                lessonNumberForCurrentLesson
                        if (lessonNumberForCurrentLesson > previousLessonNumber) {
                            listOfScheduleItemCard.add(
                                ScheduleItem.WindowItem(
                                    windowNumber = (lessonNumberForCurrentLesson - previousLessonNumber).toString(),
                                    startTime = listOfLessonTime[previousLessonNumber - 1].startTime,
                                    endTime = listOfLessonTime[lessonNumberForCurrentLesson - 2].endTime
                                )
                            )
                        }
                        listOfScheduleItemCard.add(
                            ScheduleItem.SubjectItem(
                                lesson = scheduleForDay.lessons[lessonIndex]
                            )
                        )
                        if (lessonNumberForNextLesson - lessonNumberForCurrentLesson == 1) {
                            listOfScheduleItemCard.add(
                                ScheduleItem.BreakItem(
                                    startTime = scheduleForDay.lessons[lessonIndex].lessonTime.endTime,
                                    endTime = scheduleForDay.lessons[lessonIndex + 1].lessonTime.startTime
                                )
                            )
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