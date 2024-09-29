package com.android.goally.data.repo

import androidx.lifecycle.lifecycleScope
import com.android.goally.data.db.dao.GeneralDao
import com.android.goally.data.db.entities.token.Authentication
import com.android.goally.data.model.api.response.copilot.Checklists
import com.android.goally.data.model.api.response.copilot.Routine
import com.android.goally.data.network.rest.api.GeneralApi
import com.android.goally.ui.home.HomeActivity
import kotlinx.coroutines.launch


class GeneralRepo(
    private val generalApi: GeneralApi,
    private val generalDao: GeneralDao,
) {

    suspend fun checkHealth() = generalApi.checkHealth()
    suspend fun getToken(userEmail: String) = generalApi.getToken(userEmail)
    suspend fun getDataList() = generalApi.getDataList()


    fun getAuthenticationLive() = generalDao.getAuthenticationLive()
    suspend fun getAuthentication() = generalDao.getAuthentication()

    suspend fun insertUserToken(authentication: Authentication) =
        generalDao.insertAuthentication(authentication)

    suspend fun insertApiDataCheckList(list:  Checklists) = generalDao.insertChecklistAll(list)
    suspend fun insertApiDataRoutineList(list: Routine) = generalDao.insertRoutineAll(list)

    fun getCheckListLive() = generalDao.getChecklistsLive()

    suspend fun getCheckList() = generalDao.getChecklists()

    fun getRoutineListLive() = generalDao.getRoutinesLive()

    suspend fun getRoutineList() = generalDao.getRoutines()


}