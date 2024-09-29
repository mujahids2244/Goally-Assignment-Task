package com.android.goally.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.goally.data.db.entities.token.Authentication
import com.android.goally.data.model.api.response.copilot.Checklists
import com.android.goally.data.model.api.response.copilot.Routine


@Dao
interface GeneralDao {
    @Query("Select * from authentication")
    fun getAuthenticationLive(): LiveData<Authentication?>
    @Query("Select * from authentication")
    suspend fun getAuthentication(): Authentication?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthentication(authentication: Authentication)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChecklistAll(items: Checklists)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutineAll(items: Routine)

    @Query("SELECT * FROM checklists")
    fun getChecklistsLive(): LiveData<List<Checklists>>

    @Query("SELECT * FROM checklists")
    suspend fun getChecklists(): List<Checklists>?

    @Query("SELECT * FROM routine")
    fun getRoutinesLive(): LiveData<List<Routine>>

    @Query("SELECT * FROM routine")
    suspend fun getRoutines(): List<Routine>?
}