package com.android.goally.ui.copilot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.goally.BaseActivity
import com.android.goally.R
import com.android.goally.data.model.api.response.copilot.Checklists
import com.android.goally.data.model.api.response.copilot.Routine
import com.android.goally.databinding.ActivityCopilotBinding
import com.android.goally.ui.adapters.AdapterCopilotList
import com.android.goally.ui.adapters.ChecklistAdapter
import com.android.goally.util.AppUtil
import com.android.goally.util.AppUtil.isSkeltonLoaded
import com.android.goally.util.AppUtil.showAlert
import com.android.goally.util.toast
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CopilotActivity : BaseActivity() {

    private var list: MutableList<Any> = mutableListOf()
    private var dataList: MutableList<Any> = mutableListOf()
    private lateinit var binding: ActivityCopilotBinding
    private lateinit var activityAdapter: AdapterCopilotList
    private lateinit var checklistAdapter: ChecklistAdapter
    private lateinit var skeleton: Skeleton
    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCopilotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterInit()
        skeleton = binding.ssRecyclerView.applySkeleton(R.layout.ic_row_copilot_rv, 10)
        setupApiCall()
        internetCheck()
        initMenu()

    }

    private fun internetCheck() {
        if (!AppUtil.isInternetAvailable(applicationContext)) {
            showAlert(this)
        }
    }


    private fun hideSkeleton() {
        skeleton.showOriginal()
    }

    private fun setupApiCall() {
        if (!isSkeltonLoaded) {
            skeleton.showSkeleton()
            isSkeltonLoaded = true
        }

        generalViewModel.getRoutineListLive().observe(this) { routines ->
            if (routines != null && routines.isNotEmpty()) {
                list.addAll(routines)
                activityAdapter.setData(routines)
                dataList.addAll(routines.toMutableList())
                hideSkeleton()
                isDataLoaded = true
            }
            generalViewModel.getCheckListLive().observe(this) { checklists ->
                if (checklists != null && checklists.isNotEmpty()) {
                    list.addAll(checklists)
                    activityAdapter.setData(checklists)
                    dataList.addAll(checklists.toMutableList())
                    hideSkeleton()
                } else {
                    generalViewModel.getDataListResponse(onLoading = {
                    }, onSuccess = { _, _ ->
                        hideSkeleton()
                        isDataLoaded = true
                    }, onError = { errorMessage ->
                        toast(errorMessage)
                        hideSkeleton()
                    })
                }
            }
        }

    }

    private fun adapterInit() {

        AdapterCopilotList(object : AdapterCopilotList.ClickListeners {
            override fun onView(click: Any, position: Int) {
            }
        }).also { activityAdapter = it }
        binding.ssRecyclerView.adapter = activityAdapter


        ChecklistAdapter(object : ChecklistAdapter.ClickListeners {
            override fun onSelect(click: String, position: Int, check: Boolean) {

                if (check) { //folders
                    if (click.equals("All Folders", ignoreCase = true)) {

                        activityAdapter.setDataFilter(list)

                    } else {

                        val sortedList = list.filter {
                            (it as? Checklists)?.folder == click || (it as? Routine)?.folder == click
                        }.sortedBy {
                            (it as? Checklists)?.folder ?: (it as? Routine)?.folder
                        }
                        if (sortedList.isEmpty()) {
                            binding.ssRecyclerView.visibility = View.GONE
                            binding.noData.visibility = View.VISIBLE
                        } else {
                            binding.ssRecyclerView.visibility = View.VISIBLE
                            binding.noData.visibility = View.GONE
                            activityAdapter.setDataFilter(sortedList)
                        }
                    }


                } else {//schedule
                    if (click.equals("All", ignoreCase = true)) {
                        activityAdapter.setDataFilter(list)
                    } else {
                        val sortedList = list.filter {

                            val checklistType = (it as? Checklists)?.scheduleV2?.type
                            val routineType = (it as? Routine)?.scheduleV2?.type


                            (checklistType != null && checklistType.contains(
                                click,
                                ignoreCase = true
                            )) ||
                                    (routineType != null && routineType.contains(
                                        click,
                                        ignoreCase = true
                                    ))

                        }.sortedBy {
                            (it as? Checklists)?.scheduleV2?.type
                                ?: (it as? Routine)?.scheduleV2?.type
                        }

                        if (sortedList.isEmpty()) {
                            binding.ssRecyclerView.visibility = View.GONE
                            binding.noData.visibility = View.VISIBLE

                        } else {
                            binding.ssRecyclerView.visibility = View.VISIBLE
                            binding.noData.visibility = View.GONE
                            activityAdapter.setDataFilter(sortedList)
                        }
                    }
                }

            }
        }).also { checklistAdapter = it }
        binding.menu.checklistRV.adapter = checklistAdapter

    }


    private fun initMenu() {


        binding.icBack.setOnClickListener {
            this.finish()
        }
        binding.folders.setOnClickListener {

            binding.menu.menuLayout.visibility = View.VISIBLE
            binding.menu.folders.visibility = View.VISIBLE
            binding.menu.schedule.visibility = View.GONE

            checklistAdapter.setData(dataList, true)
        }

        binding.schedule.setOnClickListener {
            binding.menu.menuLayout.visibility = View.VISIBLE
            binding.menu.folders.visibility = View.GONE
            binding.menu.schedule.visibility = View.VISIBLE
            checklistAdapter.setData(dataList, false)


        }


        binding.menu.emptyClick.setOnClickListener {
            binding.menu.menuLayout.visibility = View.GONE

        }


    }

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, CopilotActivity::class.java)
        }
    }
}