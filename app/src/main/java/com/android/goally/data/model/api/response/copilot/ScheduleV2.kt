package com.android.goally.data.model.api.response.copilot

data class ScheduleV2(

    val dailyRepeatValues: DailyRepeatValues? = DailyRepeatValues(),
    val timeType: String? = null,
    val timeValue: String? = null,
    val type: String? = null,
    val yearlyRepeatDateValue: String? = null
)