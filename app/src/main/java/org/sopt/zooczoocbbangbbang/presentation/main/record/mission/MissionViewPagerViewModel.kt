package org.sopt.zooczoocbbangbbang.presentation.main.record.mission

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody

class MissionViewPagerViewModel : ViewModel() {
    val content = MutableLiveData<String>("")
    val image: MutableLiveData<ContentUriRequestBody> = MutableLiveData()
}
