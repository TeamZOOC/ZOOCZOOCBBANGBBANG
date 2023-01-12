package org.sopt.zooczoocbbangbbang.presentation.alarm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.entity.alarm.ResponseAlarmDto
import retrofit2.HttpException
import retrofit2.await

class AlarmViewModel : ViewModel() {
    private val _alarms = MutableLiveData<List<ResponseAlarmDto.Alarm>>()
    val alarms: LiveData<List<ResponseAlarmDto.Alarm>> get() = _alarms
    private val zoocService = ServiceFactory.zoocService

    fun getAlarms() {
        viewModelScope.launch {
            kotlin.runCatching {
                zoocService.getAlarms(familyId = 1).await()
            }.onSuccess {
                Log.d("AlarmActivity", "알람 가져오기 서버 통신 성공")
                _alarms.value = it.data
            }.onFailure {
                if (it is HttpException) {
                    Log.e("AlarmActivity", "알람 가져오기 서버 통신 onResponse but not successful")
                } else {
                    Log.e("AlarmActivity", "알람 가져오기 서버 통신 onFailure")
                }
            }
        }
    }
}
