package org.sopt.zooczoocbbangbbang.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.CustomdialogDetailMoreBinding

class DetailMoreCustomDialog(private val source: View) : DialogFragment() {
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
        val sourcePos: IntArray = intArrayOf(0, 0)
        source.getLocationOnScreen(sourcePos)
        val sourceX = sourcePos[0]
        val sourceY = sourcePos[1]

        val window: Window =
            (dialog ?: error("[ERROR] 다이얼로그가 null입니다.")).window ?: error("[ERROR] window가 null입니다.")

        window.setGravity(Gravity.TOP or Gravity.START)
        val params: WindowManager.LayoutParams = window.attributes
        Log.d("asdf", "소스: ${DisplayUtil.pxToDp(requireContext(), sourceX)}")
        Log.d("asdf", "소스: ${DisplayUtil.pxToDp(requireContext(), sourceY)}")
        params.x = (sourceX + DisplayUtil.dpToPx(requireContext(), 42) - DisplayUtil.dpToPx(requireContext(), 143))
        params.y = sourceY + (source.height / 2)

        Log.d("asdf", "파람: ${DisplayUtil.pxToDp(requireContext(), params.x)}")
        Log.d("asdf", "파람: ${DisplayUtil.pxToDp(requireContext(), params.y)}")
        window.attributes = params
    }

    private fun clickView() {
        binding.root.setOnClickListener {
            delete()
            dismiss()
        }
    }

    private fun delete() {
    }
}
