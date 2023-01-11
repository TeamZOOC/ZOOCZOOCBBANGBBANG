package org.sopt.zooczoocbbangbbang.util

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentCustomDialogBinding

class CustomAlertDialog(
    val context: Context,
    private val title: String,
    private val content: String,
    private val firstChoice: String,
    private val secondChoice: String,
    private val clickFirstChoice: () -> Unit,
    private val clickSecondChoice: () -> Unit
) {
    private val dialog = Dialog(context)
    private val binding = FragmentCustomDialogBinding.inflate(LayoutInflater.from(context))

    fun showDialog() {
        initView()
        initData()
        initClickEvents()
    }

    private fun initData() {
        binding.tvDialogTitle.text = title
        binding.tvDialogSubtitle.text = content
        binding.tvDialogFirstChoice.text = firstChoice
        binding.tvDialogSecondChoice.text = secondChoice
    }

    private fun initView() {
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.rect_radius_14)
        dialog.show()
    }

    private fun initClickEvents() {
        binding.clDialogFirstChoice.setOnClickListener {
            clickFirstChoice()
            dialog.dismiss()
        }

        binding.clDialogSecondChoice.setOnClickListener {
            clickSecondChoice()
            dialog.dismiss()
        }
    }
}
