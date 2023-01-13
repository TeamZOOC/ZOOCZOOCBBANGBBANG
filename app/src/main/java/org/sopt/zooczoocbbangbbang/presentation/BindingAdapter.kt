package org.sopt.zooczoocbbangbbang.presentation // ktlint-disable filename

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.presentation.onboarding.OnboardingProgress

@BindingAdapter("progress")
fun setProgressRatio(progressBar: ProgressBar, progress: OnboardingProgress) {
    progressBar.max = 5
    progressBar.progress = progress.value
    Log.d("prg", "${progress.value}")
}

@BindingAdapter("imageOfUriString")
fun setImageOfUriString(imageView: ImageView, uriString: String?) {
    if (uriString != null) {
        val uri = Uri.parse(uriString)
        imageView.setImageURI(uri)
    } else {
        imageView.setImageResource(R.drawable.img_select_profile_image)
    }
}
