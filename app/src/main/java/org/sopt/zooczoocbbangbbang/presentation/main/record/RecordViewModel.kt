package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.util.Log
import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.presentation.main.record.mission.PetInfo
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody

class RecordViewModel : ViewModel() {
    var petInfo: PetInfo? = null
        set(value) {
            field = value
            Log.d("RecordViewModel", "PetInfo: $value")
        }
    var image: ContentUriRequestBody? = null
        set(value) {
            field = value
            Log.d("RecordViewModel", "Image: $value")
        }
    var text: String? = null
        set(value) {
            field = value
            Log.d("RecordViewModel", "Text: $value")
        }
}
