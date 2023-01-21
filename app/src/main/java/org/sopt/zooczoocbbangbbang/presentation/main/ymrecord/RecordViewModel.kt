package org.sopt.zooczoocbbangbbang.presentation.main.ymrecord

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody
import retrofit2.HttpException
import retrofit2.await
import java.io.File

class RecordViewModel : ViewModel() {
    private val zoocService = ServiceFactory.zoocService

    val buttonEnable = MutableLiveData<Boolean>(false)

    val imageRequestBody = MutableLiveData<ContentUriRequestBody>(null)
    val textRequestBody = MutableLiveData<RequestBody>(null)
    var petParts: List<MultipartBody.Part> = emptyList()

    val isSuccess = MutableLiveData<Boolean>()
    val fragmentState = MutableLiveData<RegisterFragmentState>(RegisterFragmentState.DAILY)

    fun postRecord() {
        viewModelScope.launch {
            kotlin.runCatching {
                zoocService.postYmRecord(
                    familyId = 10,
                    contentBody = textRequestBody.value ?: throw IllegalArgumentException(),
                    file = imageRequestBody.value?.toFormData(),
                    pet = petParts
                ).await()
            }.onSuccess {
                isSuccess.value = true
            }.onFailure {
                isSuccess.value = false
                if (it is HttpException) {
                    Log.e("RecordActivity", "기록하기 서버 통신 onResponse but not successful, ${it.message}")
                } else {
                    Log.e("RecordActivity", "기록하기 서버 통신 onFailure, ${it.message}")
                }
            }
        }
    }

    private fun convertToMultipartText(content: String): RequestBody {
        Log.d("ym multipart", "convertToMultipartText 들어옴")
        return content.toRequestBody("text/plain".toMediaTypeOrNull())
    }

    private fun convertToMultiPartFile(image: Uri): MultipartBody.Part {
        Log.d("ym multipart", "convertToMultiPartFile 들어옴")
        val file = File(image.path)
        val imageRequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", file.name, imageRequestBody)
    }
}
