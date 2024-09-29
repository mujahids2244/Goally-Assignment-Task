package com.android.goally.ui.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.goally.R
import com.android.goally.data.model.api.response.copilot.Checklists
import com.android.goally.data.model.api.response.copilot.Routine
import com.android.goally.databinding.IcRowCopilotRvBinding
import com.bumptech.glide.Glide


class AdapterCopilotList(
    private var listener: ClickListeners
) : RecyclerView.Adapter<AdapterCopilotList.Holder>() {
    private var sList: MutableList<Any> = mutableListOf()
    private val scheduleList =
        listOf("ANY_TIME", "REPEATING_YEARLY", "REPEATING_WEEKLY", "REPEATING_DAILY")


    fun setData(slist: List<Any>) {
        this.sList.addAll(slist)
        notifyDataSetChanged()
    }

    fun setDataFilter(slist: List<Any>) {
        this.sList = slist.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = IcRowCopilotRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        if (sList.isEmpty()) {
            holder.binding.recyclerHeading.visibility = View.GONE
        } else {
            val list = sList[position]

            holder.binding.recyclerHeading.visibility = View.VISIBLE

            when (list) {
                is Checklists -> {
                    Glide.with(holder.itemView.context).load(list.visualAidUrl)
                        .placeholder(R.drawable.ic_placeholder_image).into(holder.binding.img)

                    holder.binding.title.text = list.name ?: "N/A"
                    if (list.isScheduledByV2 != null) {
                        if (list.isScheduledByV2) {
                            val schedule = scheduleList.indexOf(list.scheduleV2?.type)

                            when (schedule) {
                                0 -> {
                                    holder.binding.scheduleTV.text = "Not Schedule"
                                }

                                1 -> {
                                    holder.binding.scheduleTV.text =
                                        list.scheduleV2?.yearlyRepeatDateValue ?: "N/A"

                                }

                                2 -> {
                                    holder.binding.scheduleTV.text = "Sat, Sun"

                                }

                                3 -> {
                                    holder.binding.scheduleTV.text = "EveryDay"

                                }

                                else -> {
                                    holder.binding.scheduleTV.text = "Not Schedule"

                                }
                            }


                        } else {
                            holder.binding.scheduleTV.text = "Not Schedule"

                        }


                    } else {
                        holder.binding.scheduleTV.text = "Not Schedule"

                    }
                    holder.binding.folderTV.text = list.folder
                }

                is Routine -> {
                    Glide.with(holder.itemView.context).load(list.imgURL)
                        .placeholder(R.drawable.ic_placeholder_image).into(holder.binding.img)
                    holder.binding.title.text = list.name ?: "N/A"
                    if (list.isScheduledByV2 != null) {
                        if (list.isScheduledByV2) {
                            val schedule = scheduleList.indexOf(list.scheduleV2?.type)

                            when (schedule) {
                                0 -> {

                                    holder.binding.scheduleTV.text = "Not Schedule"
                                }

                                1 -> {
                                    holder.binding.scheduleTV.text =
                                        list.scheduleV2?.yearlyRepeatDateValue ?: "N/A"

                                }

                                2 -> {
                                    holder.binding.scheduleTV.text = "Sat, Sun"

                                }

                                3 -> {
                                    holder.binding.scheduleTV.text = "EveryDay"

                                }

                                else -> {
                                    holder.binding.scheduleTV.text = "Not Schedule"

                                }
                            }

                        } else {
                            holder.binding.scheduleTV.text = "Not Schedule"

                        }


                    } else {
                        holder.binding.scheduleTV.text = "Not Schedule"

                    }
                    holder.binding.folderTV.text = list.folder
                }
            }
            holder.onBind(list, listener, position)
        }


    }


    override fun getItemCount(): Int = if (sList.size == 0) {
        1
    } else sList.size


    inner class Holder(val binding: IcRowCopilotRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Any, listener: ClickListeners, position: Int) {
            binding.recyclerHeading.setOnClickListener {
                listener.onView(item, position)
            }
        }
    }

    interface ClickListeners {
        fun onView(click: Any, position: Int)
    }


}