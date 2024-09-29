package com.android.goally.data.db

import androidx.room.TypeConverter
import com.android.goally.data.model.api.response.copilot.Activity
import com.android.goally.data.model.api.response.copilot.ActivityX
import com.android.goally.data.model.api.response.copilot.AudioEvent
import com.android.goally.data.model.api.response.copilot.AudioEventX
import com.android.goally.data.model.api.response.copilot.ChecklistNotifications
import com.android.goally.data.model.api.response.copilot.ChecklistNotificationsV2
import com.android.goally.data.model.api.response.copilot.Checklists
import com.android.goally.data.model.api.response.copilot.LastSchedule
import com.android.goally.data.model.api.response.copilot.Routine
import com.android.goally.data.model.api.response.copilot.RoutineNotification
import com.android.goally.data.model.api.response.copilot.RoutineNotificationsV2
import com.android.goally.data.model.api.response.copilot.Schedule
import com.android.goally.data.model.api.response.copilot.ScheduleV2
import com.android.goally.data.model.api.response.copilot.ScheduleV2X
import com.android.goally.data.model.api.response.copilot.ScheduleX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConvert {

    private val gson = Gson()
    @TypeConverter
    fun fromList(value: List<String>?): String? {
        return value?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toList(value: String?): List<String>? {
        return value?.let {
            Gson().fromJson(value, Array<String>::class.java).toList()
        }
    }


    // For List<AudioEvent>
    @TypeConverter
    fun fromAudioEventList(value: List<AudioEvent>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toAudioEventList(value: String?): List<AudioEvent>? {
        return value?.let {
            val listType = object : TypeToken<List<AudioEvent>?>() {}.type
            gson.fromJson(value, listType)
        }
    }

    // For List<ChecklistNotifications>
    @TypeConverter
    fun fromChecklistNotificationsList(value: List<ChecklistNotifications>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toChecklistNotificationsList(value: String?): List<ChecklistNotifications>? {
        return value?.let {
            val listType = object : TypeToken<List<ChecklistNotifications>?>() {}.type
            gson.fromJson(value, listType)
        }
    }

    // For List<ChecklistNotificationsV2>
    @TypeConverter
    fun fromChecklistNotificationsV2List(value: List<ChecklistNotificationsV2>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toChecklistNotificationsV2List(value: String?): List<ChecklistNotificationsV2>? {
        return value?.let {
            val listType = object : TypeToken<List<ChecklistNotificationsV2>?>() {}.type
            gson.fromJson(value, listType)
        }
    }



    @TypeConverter
    fun fromAudioEventXList(value: List<AudioEventX>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toAudioEventXList(value: String?): List<AudioEventX>? {
        return value?.let {
            val listType = object : TypeToken<List<AudioEventX>?>() {}.type
            gson.fromJson(value, listType)
        }
    }
    // General List Converters
    @TypeConverter
    fun fromList2(value: List<Any?>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toList2(value: String?): List<Any?>? {
        return value?.let {
            val listType = object : TypeToken<List<Any?>?>() {}.type
            gson.fromJson(value, listType)
        }
    }



    // Specific Type Converters for Activity
    @TypeConverter
    fun fromActivityList(value: List<Activity?>?): String? {
        return fromList2(value)
    }

    @TypeConverter
    fun toActivityList(value: String?): List<Activity?>? {
        return value?.let {
            val listType = object : TypeToken<List<Activity?>?>() {}.type
            gson.fromJson(value, listType)
        }
    }

    // Specific Type Converters for ActivityX
    @TypeConverter
    fun fromActivityListRoutine(value: List<ActivityX>?): String? {
        return fromList2(value)
    }

    @TypeConverter
    fun toActivityListRoutine(value: String?): List<ActivityX>? {
        return value?.let {
            val listType = object : TypeToken<List<ActivityX>?>() {}.type
            gson.fromJson(value, listType)
        }
    }

//    // String List Converters
//    @TypeConverter
//    fun fromStringList(value: List<String?>?): String? {
//        return fromList2(value)
//    }
//
//    @TypeConverter
//    fun toStringList(value: String?): List<String?>? {
//        return value?.let {
//            val listType = object : TypeToken<List<String?>?>() {}.type
//            gson.fromJson(value, listType)
//        }
//    }

    // Specific List Converters for RoutineNotification
    @TypeConverter
    fun fromRoutineNotificationList(value: List<RoutineNotification>?): String? {
        return fromList2(value)
    }

    @TypeConverter
    fun toRoutineNotificationList(value: String?): List<RoutineNotification>? {
        return value?.let {
            val listType = object : TypeToken<List<RoutineNotification>?>() {}.type
            gson.fromJson(value, listType)
        }
    }

    // Specific List Converters for RoutineNotificationsV2
    @TypeConverter
    fun fromRoutineNotificationsV2List(value: List<RoutineNotificationsV2>?): String? {
        return fromList2(value)
    }

    @TypeConverter
    fun toRoutineNotificationsV2List(value: String?): List<RoutineNotificationsV2>? {
        return value?.let {
            val listType = object : TypeToken<List<RoutineNotificationsV2>?>() {}.type
            gson.fromJson(value, listType)
        }
    }

    // Convert LastSchedule to String
    @TypeConverter
    fun fromLastSchedule(value: LastSchedule?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toLastSchedule(value: String?): LastSchedule? {
        return value?.let { gson.fromJson(it, LastSchedule::class.java) }
    }

    // Schedule Converters
    @TypeConverter
    fun fromSchedule(value: Schedule?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toSchedule(value: String?): Schedule? {
        return value?.let { gson.fromJson(it, Schedule::class.java) }
    }

    @TypeConverter
    fun fromScheduleX(value: ScheduleX?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toScheduleX(value: String?): ScheduleX? {
        return value?.let { gson.fromJson(it, ScheduleX::class.java) }
    }

    // ScheduleV2X Converters
    @TypeConverter
    fun fromScheduleV2X(value: ScheduleV2X?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toScheduleV2X(value: String?): ScheduleV2X? {
        return value?.let { gson.fromJson(it, ScheduleV2X::class.java) }
    }
    // ScheduleV2X Converters
    @TypeConverter
    fun fromScheduleV2(value: ScheduleV2?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toScheduleV2(value: String?): ScheduleV2? {
        return value?.let { gson.fromJson(it, ScheduleV2::class.java) }
    }

    // Convert Checklists? to String
    @TypeConverter
    fun fromChecklistsList(value: MutableList<Checklists>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toChecklistsList(value: String): MutableList<Checklists>? {
        val listType = object : TypeToken<MutableList<Checklists>?>() {}.type
        return gson.fromJson(value, listType)
    }

    // Convert Routine? to String
    @TypeConverter
    fun fromRoutineList(value: MutableList<Routine>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toRoutineList(value: String): MutableList<Routine>? {
        val listType = object : TypeToken<MutableList<Routine>?>() {}.type
        return gson.fromJson(value, listType)
    }

}