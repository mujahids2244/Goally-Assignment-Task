package com.android.goally.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.goally.data.model.api.response.copilot.Checklists
import com.android.goally.data.model.api.response.copilot.Routine
import com.android.goally.databinding.IcItemChecklistBinding

class ChecklistAdapter(
    private val listener: ClickListeners,
) : RecyclerView.Adapter<ChecklistAdapter.Holder>() {
    private var check: Boolean = false
    private val scheduleList =
        listOf("ANY_TIME", "REPEATING_YEARLY", "REPEATING_WEEKLY", "REPEATING_DAILY")
    private var sList2: List<Any> = mutableListOf()
    private var distinctFolders = mutableSetOf<String>()
    private var displayList = mutableListOf<String>()
    private var selectedPosition: Int = RecyclerView.NO_POSITION

    fun setData(list: List<Any>, check: Boolean) {
        this.selectedPosition = RecyclerView.NO_POSITION
        this.check = check
        sList2 = mutableListOf()
        displayList = mutableListOf()
        distinctFolders = mutableSetOf()
        sList2 = list
        val checkListItems = sList2.toMutableList()

        checkListItems.forEach { checkList ->
            when (checkList) {
                is Checklists -> {
                    if (check) {
                        checkList.folder?.let { folder ->
                            if (distinctFolders.add(folder)) {
                                displayList.add(folder)
                            }
                        }
                    } else {
                        checkList.isScheduledByV2?.let {
                            val type = checkList.scheduleV2?.type
                            if (type != null) { // Ensure type is not null before comparison
                                when (type) {
                                    scheduleList[0] -> {
                                        if (distinctFolders.add("Anytime")) {
                                            displayList.add("Anytime")
                                        }
                                    }

                                    scheduleList[1] -> {
                                        if (distinctFolders.add("Repeating")) {
                                            displayList.add("Repeating")
                                        }
                                    }

                                    scheduleList[2] -> {
                                        if (distinctFolders.add("Repeating")) {
                                            displayList.add("Repeating")
                                        }
                                    }

                                    scheduleList[3] -> {
                                        if (distinctFolders.add("Repeating")) {
                                            displayList.add("Repeating")
                                        }
                                    }

                                    else -> {
                                        if (distinctFolders.add("Anytime")) {
                                            displayList.add("Anytime")
                                        }
                                    }
                                }

                            }
                        }
                    }
                }

                is Routine -> {
                    if (check) {
                        checkList.folder?.let { folder ->
                            if (distinctFolders.add(folder)) {
                                displayList.add(folder)
                            }
                        }
                    } else {
                        checkList.isScheduledByV2?.let {
                            val type = checkList.scheduleV2?.type
                            if (type != null) { // Ensure type is not null before comparison
                                when (type) {
                                    scheduleList[0] -> {
                                        if (distinctFolders.add("Anytime")) {
                                            displayList.add("Anytime")
                                        }
                                    }

                                    scheduleList[1] -> {
                                        if (distinctFolders.add("Repeating")) {
                                            displayList.add("Repeating")
                                        }
                                    }

                                    scheduleList[2] -> {
                                        if (distinctFolders.add("Repeating")) {
                                            displayList.add("Repeating")
                                        }
                                    }

                                    scheduleList[3] -> {
                                        if (distinctFolders.add("Repeating")) {
                                            displayList.add("Repeating")
                                        }
                                    }

                                    else -> {
                                        if (distinctFolders.add("Anytime")) {
                                            displayList.add("Anytime")
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }

        if (check) {
            if (displayList.isEmpty()) {
                displayList.add("All Folders")
            } else {
                displayList.add(0, "All Folders")
            }

            val otherItem = displayList.find { it.contains("OTHER") }
            if (otherItem != null) {
                displayList.remove(otherItem)
                displayList.add(otherItem)
            }
        } else {
            if (displayList.isEmpty()) {
                displayList.add("All")
            } else {
                displayList.add(0, "All")
            }

        }

        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = IcItemChecklistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )


        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val bind = holder.binding

        if (displayList.isEmpty()) {

            bind.mainLayout.visibility = View.GONE
        } else {
            val checkList = displayList[position]

            bind.mainLayout.visibility = View.VISIBLE

            bind.check.text = checkList
            if (check) {
                val totalCount = sList2.count { (it as? Routine)?.folder == checkList } +
                        sList2.count { (it as? Checklists)?.folder == checkList }
                bind.count.text = totalCount.toString()

            } else {
                val totalCount = sList2.count { item ->
                    when (item) {
                        is Routine -> item.scheduleV2?.type in scheduleList
                        is Checklists -> item.scheduleV2?.type in scheduleList
                        else -> false
                    }
                }

                bind.count.text = totalCount.toString()

            }

            if (position == 0) {
                bind.count.text = sList2.size.toString()
            }


            holder.bind(checkList, listener, position, check)
        }
    }

    override fun getItemCount(): Int = displayList.size

    inner class Holder(val binding: IcItemChecklistBinding) : ViewHolder(binding.root) {
        fun bind(item: String, listener: ClickListeners, position: Int, check: Boolean) {

            binding.check.isChecked =
                adapterPosition == selectedPosition // Check if this item is selected


            binding.check.setOnClickListener {
                selectedPosition = adapterPosition
                if (item.equals("Anytime", ignoreCase = true)) {
                    listener.onSelect("ANY_TIME", position, check)

                } else if (item.equals("Repeating", true)) {
                    listener.onSelect("REPEATING", position, check)

                } else if (item.equals("All", true)) {
                    listener.onSelect("All", position, check)

                } else {
                    listener.onSelect(item, position, check)

                }
                notifyDataSetChanged()
            }
        }
    }

    interface ClickListeners {
        fun onSelect(click: String, position: Int, check: Boolean)
    }
}

