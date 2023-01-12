package org.sopt.zooczoocbbangbbang.presentation.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.entity.common.Comment
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.request.RequestCommentDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.request.RequestEmojiDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.response.ResponseRecordDetailDto
import retrofit2.HttpException
import retrofit2.await

class DetailViewModel : ViewModel() {
    private val zoocService = ServiceFactory.zoocService
    val recordDetail = MutableLiveData<ResponseRecordDetailDto.RecordDetailDto>()
    val comments = MutableLiveData<List<Comment>>()
    val emojiId = MutableLiveData<Int>()

    fun uploadComment(recordId: Int, comment: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                zoocService.postTextComment(recordId, RequestCommentDto(comment)).await()
            }.onSuccess {
                comments.value = it.data
            }.onFailure {
                if (it is HttpException) {
                    Log.e("DetailFragment", "댓글 달기 서버 통신 onResponse but not successful")
                } else {
                    Log.e("DetailFragment", "댓글 달기 서버 통신 onFailure")
                }
            }
        }
    }

    fun uploadEmoji(recordId: Int, emojiId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                zoocService.postEmojiComment(recordId, RequestEmojiDto(emojiId)).await()
            }.onSuccess {
                comments.value = it.data
            }.onFailure {
                if (it is HttpException) {
                    Log.e("DetailFragment", "이모지 달기 서버 통신 onResponse but not successful")
                } else {
                    Log.e("DetailFragment", "이모지 달기 서버 통신 onFailure")
                }
            }
        }
    }

    fun getDetailData(recordId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                zoocService.getRecordDetail(1, recordId).await()
            }.onSuccess {
                recordDetail.value = it.data
                comments.value = it.data.comments
            }.onFailure {
                if (it is HttpException) {
                    Log.e("DetailFragment", "상세 보기 서버 통신 onResponse but not successful")
                } else {
                    Log.e("DetailFragment", "상세 보기 서버 통신 onFailure")
                }
            }
        }
    }
}
