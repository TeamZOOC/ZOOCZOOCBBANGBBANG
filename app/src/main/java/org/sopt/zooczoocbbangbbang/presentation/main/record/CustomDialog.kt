package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.sopt.zooczoocbbangbbang.databinding.FragmentCustomDialogBinding

class CustomDialog(val finishApp: () -> Unit?) : DialogFragment() {
    private var _binding: FragmentCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setGravity(Gravity.CENTER)
        clickRelayWriteBtn()
        clickQuitBtn()
        return view
    }

    private fun clickRelayWriteBtn() {
        binding.clDialogFirstChoice.setOnClickListener() {
            // Timber.tag("CustomDialogFragment").d("다이얼로그 닫기 로직 필요!")
            dismiss()
        }
    }

    private fun clickQuitBtn() {
        binding.clDialogSecondChoice.setOnClickListener() {
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
