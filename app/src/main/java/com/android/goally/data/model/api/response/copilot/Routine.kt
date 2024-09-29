package com.android.goally.data.model.api.response.copilot

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Routine(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    @SerializedName("__id")
    val id: Int = 0,


    @ColumnInfo val __v: Int? = 0,
    @ColumnInfo val _id: String? = "",
    @ColumnInfo val activities: List<ActivityX>? = listOf(),
    @ColumnInfo val allowClientToCancel: Boolean? = false,
    @ColumnInfo val allowEarlyStart: Boolean? = false,
    @ColumnInfo val allowRecap: Boolean? = false,
    @ColumnInfo val allowSnooze: Boolean? = false,
    @ColumnInfo val allowToOverride: Boolean? = false,
    @ColumnInfo val allowUpdateNightLightAndNoiseMachine: Boolean? = false,
    @ColumnInfo val appRewards: List<Any?>? = listOf(),
    @ColumnInfo val audioEvents: List<AudioEventX>? = listOf(),
    @ColumnInfo val category: String? = "",
    @ColumnInfo val clientId: String? = "",
    @ColumnInfo val createdAt: String? = "",
    @ColumnInfo val createdBy: String? = "",
    @ColumnInfo val ctaOrdering: Int? = 0,
    @ColumnInfo val devices: List<String>? = listOf(),
    @ColumnInfo val earlyStartMinutes: Int? = 0,
    @ColumnInfo val enableChillZoneWatch: Boolean? = false,
    @ColumnInfo val enableEmotionalFeedback: Boolean? = false,
    @ColumnInfo val enableSpeedMonitoring: Boolean? = false,
    @ColumnInfo val enableVacationMode: Boolean? = false,
    @ColumnInfo val enableWeatherWatch: Boolean? = false,
    @ColumnInfo val entityType: String? = "",
    @ColumnInfo val folder: String? = "",
    @ColumnInfo val folderId: String? = "",
    @ColumnInfo val imgURL: String? = "",
    @ColumnInfo val isCreatedByDevice: Boolean? = false,
    @ColumnInfo val isMarkedHot: Boolean? = false,
    @ColumnInfo val isScheduledByV2: Boolean? = false,
    @ColumnInfo val isVisibleToAudience: Boolean? = false,
    @ColumnInfo val label: String? = "",
    @ColumnInfo val lastSchedule: LastSchedule? = LastSchedule(),
    @ColumnInfo val libraryType: String? = "",
    @ColumnInfo val limitEarlyStart: Boolean? = false,
    @ColumnInfo val limitRunPerDay: Boolean? = false,
    @ColumnInfo val limitRunPerHour: Boolean? = false,
    @ColumnInfo val migrated: Boolean? = false,
    @ColumnInfo val name: String? = "",
    @ColumnInfo val narration: Boolean? = false,
    @ColumnInfo val notifsV2SoundName: String? = "",
    @ColumnInfo val notifsV2SoundUrl: String? = "",
    @ColumnInfo val numberOfPointsLate: Int? = 0,
    @ColumnInfo val numberOfPointsOnTime: Int? = 0,
    @ColumnInfo val numberOfPuzzlesLate: Int? = 0,
    @ColumnInfo val numberOfPuzzlesOnTime: Int? = 0,
    @ColumnInfo val numberOfRunPerDay: Int? = 0,
    @ColumnInfo val numberOfRunPerHour: Int? = 0,
    @ColumnInfo val numberOfSnoozeAllowed: Int? = 0,
    @ColumnInfo val openAIMp3Text: String? = "",
    @ColumnInfo val openAIMp3Url: String? = "",
    @ColumnInfo val ordering: Int? = 0,
    @ColumnInfo val parentRoutineId: String? = "",
    @ColumnInfo val requiredRewardApproval: Boolean? = false,
    @ColumnInfo val rewardType: String? = "",
    @ColumnInfo val routineFolder: String? = "",
    @ColumnInfo val routineNotifications: List<RoutineNotification>? = listOf(),
    @ColumnInfo val routineNotificationsV2: List<RoutineNotificationsV2>? = listOf(),
    @ColumnInfo val schedule: ScheduleX? = ScheduleX(),
    @ColumnInfo val scheduleV2: ScheduleV2X? = ScheduleV2X(),
    @ColumnInfo val showOnLearnerApp: Boolean? = false,
    @ColumnInfo val showTimer: Boolean? = false,
    @ColumnInfo val tags: List<String>? = listOf(),
    @ColumnInfo val templateDisabledClientIds: List<Any?>? = listOf(),
    @ColumnInfo val type: String? = "",
    @ColumnInfo val updatedAt: String? = ""
)