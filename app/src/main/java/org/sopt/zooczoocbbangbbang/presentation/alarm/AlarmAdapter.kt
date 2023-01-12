package org.sopt.zooczoocbbangbbang.presentation.alarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.data.remote.entity.alarm.ResponseAlarmDto
import org.sopt.zooczoocbbangbbang.databinding.ItemAlarmBinding

class AlarmAdapter : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {
    private var alarms: List<ResponseAlarmDto.Alarm> = emptyList()

    class AlarmViewHolder(private val binding: ItemAlarmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseAlarmDto.Alarm) {
            binding.data = data
            binding.ivAlarmUserImage.load(data.writer.photo ?: R.drawable.ic_default_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAlarmBinding.inflate(layoutInflater, parent, false)
        return AlarmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.onBind(alarms[position])
    }

    override fun getItemCount(): Int = alarms.size

    fun initAlarms(alarms: List<ResponseAlarmDto.Alarm>) {
        this.alarms = alarms.toList()
        notifyDataSetChanged()
    }
}
