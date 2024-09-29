package com.android.goally.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.goally.data.db.entities.token.Authentication
import com.android.goally.data.model.api.response.copilot.Checklists
import com.android.goally.data.model.api.response.copilot.Routine
import com.android.goally.data.repo.GeneralRepo
import com.android.goally.ui.home.HomeActivity
import com.android.goally.util.LogUtil
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val generalRepo: GeneralRepo
) : ViewModel() {

    fun checkServerHealth(
        onLoading: (Boolean) -> Unit,
        onError: (String) -> Unit,
        onSuccess: (String) -> Unit
    ) {
        onLoading(true)
        viewModelScope.launch {
            when (val res = generalRepo.checkHealth()) {
                is NetworkResponse.Success -> {
                    LogUtil.i(res.body.toString())
                    if (res.body?.status.equals("ok", true)) {
                        onSuccess("Server is up")
                    } else {
                        onError("Server is down")
                    }
                    onLoading(false)
                }

                is NetworkResponse.ServerError -> {
                    LogUtil.e(res.code.toString())
                    LogUtil.e(res.body?.message)
                    onError(res.body?.message ?: "Server error")
                    onLoading(false)
                }

                is NetworkResponse.NetworkError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Network error")
                    onLoading(false)
                }

                is NetworkResponse.UnknownError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Unknown error")
                    onLoading(false)
                }
            }
        }
    }

    fun getTokenFor(
        userEmail: String,
        onLoading: (Boolean) -> Unit,
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) {
        onLoading(true)
        viewModelScope.launch {
            when (val res = generalRepo.getToken(userEmail)) {
                is NetworkResponse.Success -> {
                    LogUtil.i(res.body.toString())
                    res.body?.let {
                        if (!it.token.isNullOrEmpty() && !it.name.isNullOrEmpty()) {
                            //save token here which will be used for further api calls
                            val authentication = Authentication(token = it.token, name = it.name!!)
                            generalRepo.insertUserToken(authentication)
                            onSuccess()
                        }
                    } ?: run {
                        onError("Something went wrong")
                    }
                    onLoading(false)
                }

                is NetworkResponse.ServerError -> {
                    LogUtil.e(res.code.toString())
                    LogUtil.e(res.body?.message)
                    onError(res.body?.message ?: "Server error")
                    onLoading(false)
                }

                is NetworkResponse.NetworkError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Network error")
                    onLoading(false)
                }

                is NetworkResponse.UnknownError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Unknown error")
                    onLoading(false)
                }
            }
        }
    }


    fun getDataListResponse(
        onLoading: (Boolean) -> Unit,
        onError: (String) -> Unit,
        onSuccess: (MutableList<Checklists>, MutableList<Routine>) -> Unit
    ) {
        onLoading(true)
        viewModelScope.launch {
            when (val res = generalRepo.getDataList()) {
                is NetworkResponse.Success -> {
                    LogUtil.i(res.body.toString())
                    res.body.let { it ->

                        val routines = it.routines ?: mutableListOf()
                        val checklists = it.checklists ?: mutableListOf()

                        if (routines.isNotEmpty() || checklists.isNotEmpty()) {
                            val existingData = generalRepo.getRoutineList()
                            val existingData2 = generalRepo.getCheckList()

                            if (existingData?.isNotEmpty()!!) {
                                val newData = routines.filterNot { routine ->
                                    existingData.contains(routine)
                                }
                                if (newData.isNotEmpty()) {

                                    coroutineScope {
                                        launch(Dispatchers.IO) {
                                            newData.forEach {
                                                generalRepo.insertApiDataRoutineList(it)

                                            }
                                        }
                                    }


                                }
                            } else {
                                coroutineScope {
                                    launch(Dispatchers.IO) {
                                        routines.forEach {
                                            generalRepo.insertApiDataRoutineList(it)

                                        }
                                    }
                                }

                            }


                            if (existingData2?.isNotEmpty()!!) {
                                val newData2 = checklists.filterNot { checklist ->
                                    existingData2.contains(checklist)
                                }
                                if (newData2.isNotEmpty()) {

                                    coroutineScope {
                                        launch(Dispatchers.IO) {
                                            newData2.forEach {
                                                generalRepo.insertApiDataCheckList(it)

                                            }
                                        }
                                    }
                                }
                            } else {
                                coroutineScope {
                                    launch(Dispatchers.IO) {
                                        checklists.forEach {
                                            generalRepo.insertApiDataCheckList(it)

                                        }
                                    }
                                }
                            }
                            onSuccess(checklists, routines)
                        }
                        else {
                            onError("No Data Found.")
                        }
                    } ?: run {
                        onError("Something went wrong")
                    }
                    onLoading(false)
                }

                is NetworkResponse.ServerError -> {
                    LogUtil.e(res.code.toString())
                    LogUtil.e(res.body?.message)
                    onError(res.body?.message ?: "Server error")
                    onLoading(false)
                }

                is NetworkResponse.NetworkError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Network error")
                    onLoading(false)
                }

                is NetworkResponse.UnknownError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Unknown error")
                    onLoading(false)
                }
            }
        }
    }

    fun getAuthenticationLive() = generalRepo.getAuthenticationLive()
    suspend fun getAuthentication() = generalRepo.getAuthentication()


    fun getCheckListLive() = generalRepo.getCheckListLive()
    fun getRoutineListLive() = generalRepo.getRoutineListLive()


}