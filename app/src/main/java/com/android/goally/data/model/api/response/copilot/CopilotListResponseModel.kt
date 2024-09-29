package com.android.goally.data.model.api.response.copilot

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class CopilotListResponseModel(

    @ColumnInfo
    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id") val id: Int = 0,
    @ColumnInfo val checklists: MutableList<Checklists>? = mutableListOf(),
    @ColumnInfo val routines: MutableList<Routine>? = mutableListOf()
)