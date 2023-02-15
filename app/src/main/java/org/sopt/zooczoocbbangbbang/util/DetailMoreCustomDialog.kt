package org.sopt.zooczoocbbangbbang.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.CustomdialogDetailMoreBinding

class DetailMoreCustomDialog(
    private val viewAssociatedPositionCalculator: ViewAssociatedPositionCalculator,
    private val deleteRecord: () -> Unit
) : DialogFragment() {
    private lateinit var binding: CustomdialogDetailMoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.TransparentDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CustomdialogDetailMoreBinding.inflate(layoutInflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        clickView()
        setDialogPosition()
        return binding.root
    }

    private fun setDialogPosition() {
        val window: Window =
            (dialog ?: error("[ERROR] 다이얼로그가 null입니다."))
                .window ?: error("[ERROR] window가 null입니다.")
        window.setGravity(Gravity.TOP or Gravity.START)

        val params: WindowManager.LayoutParams = window.attributes
        params.x = viewAssociatedPositionCalculator.calculatePosX()
        params.y = viewAssociatedPositionCalculator.calculatePosY()

        window.attributes = params
    }

    private fun clickView() {
        binding.root.setOnClickListener {
            deleteRecord()
            dismiss()
        }
    }
}
