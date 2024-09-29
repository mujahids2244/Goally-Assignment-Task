package com.android.goally.data.model.api.response.copilot

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Checklists(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    @SerializedName("___id")
    val id: Int = 0,
    @ColumnInfo val __v: Int? = null,
    @ColumnInfo val _id: String? = null,
    @ColumnInfo val activities: List<Activity?>? = null,
    @ColumnInfo val allowCancel: Boolean? = null,
    @ColumnInfo val allowComplete: Boolean? = null,
    @ColumnInfo val allowPause: Boolean? = null,
    @ColumnInfo val allowRestart: Boolean? = null,
    @ColumnInfo val allowSnooze: Boolean? = null,
    @ColumnInfo val allowToOverride: Boolean? = null,
    @ColumnInfo val appRewards: List<Any?>? = null,
    @ColumnInfo val audioEvents: List<AudioEvent?>? = null,
    @ColumnInfo val checklistNotifications: List<ChecklistNotifications?>? = null,
    @ColumnInfo val checklistNotificationsV2: List<ChecklistNotificationsV2?>? = null,
    @ColumnInfo val clientId: String? = null,
    @ColumnInfo val createdAt: String? = null,
    @ColumnInfo val createdBy: String? = null,
    @ColumnInfo val ctaOrdering: Int? = null,
    @ColumnInfo val devices: List<String?>? = null,
    @ColumnInfo val duration: Int? = null,
    @ColumnInfo val earlyStartMinutes: Int? = null,
    @ColumnInfo val enableAudioAid: Boolean? = null,
    @ColumnInfo val enableEmotionalFeedback: Boolean? = null,
    @ColumnInfo val enableVacationMode: Boolean? = null,
    @ColumnInfo val entityType: String? = null,
    @ColumnInfo val folder: String? = null,
    @ColumnInfo val folderId: String? = null,
    @ColumnInfo val hideActAfterCom: Boolean? = null,
    @ColumnInfo val isScheduledByV2: Boolean? = null,
    @ColumnInfo val label: String? = null,
    @ColumnInfo val lastSchedule: LastSchedule? = null,
    @ColumnInfo val libraryType: String? = null,
    @ColumnInfo val limitEarlyStart: Boolean? = null,
    @ColumnInfo val limitRunPerDay: Boolean? = null,
    @ColumnInfo val limitRunPerHour: Boolean? = null,
    @ColumnInfo val minDuration: Int? = null,
    @ColumnInfo val name: String? = null,
    @ColumnInfo val narration: Boolean? = null,
    @ColumnInfo val notifsV2SoundName: String? = null,
    @ColumnInfo val notifsV2SoundUrl: String? = null,
    @ColumnInfo val numberOfPointsLate: Int? = null,
    @ColumnInfo val numberOfPointsOnTime: Int? = null,
    @ColumnInfo val numberOfPuzzlesLate: Int? = null,
    @ColumnInfo val numberOfPuzzlesOnTime: Int? = null,
    @ColumnInfo val numberOfRunPerDay: Int? = null,
    @ColumnInfo val numberOfRunPerHour: Int? = null,
    @ColumnInfo val numberOfSnoozeAllowed: Int? = null,
    @ColumnInfo val ordering: Int? = null,
    @ColumnInfo val requiredRewardApproval: Boolean? = null,
    @ColumnInfo val rewardType: String? = null,
    @ColumnInfo val schedule: Schedule? = null,
    @ColumnInfo val scheduleV2: ScheduleV2? = null,
    @ColumnInfo val showOnLearnerApp: Boolean? = null,
    @ColumnInfo val showTimer: Boolean? = null,
    @ColumnInfo val tags: List<String?>? = null,
    @ColumnInfo val type: String? = null,
    @ColumnInfo val updatedAt: String? = null,
    @ColumnInfo val visualAidUrl: String? = null

)