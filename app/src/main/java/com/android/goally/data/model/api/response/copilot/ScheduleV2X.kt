package com.android.goally.data.model.api.response.copilot

data class ScheduleV2X(
    val dailyRepeatValues: DailyRepeatValues? = DailyRepeatValues(),
    val timeType: String? = "",
    val timeValue: String? = "",
    val type: String? = "",
    val yearlyRepeatDateValue: String? = ""
)