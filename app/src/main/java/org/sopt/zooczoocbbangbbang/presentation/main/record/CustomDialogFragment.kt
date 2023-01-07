package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import android.util.Log
import android.view.View
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentCustomDialogBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class CustomDialogFragment : BindingFragment<FragmentCustomDialogBinding>(R.layout.fragment_custom_dialog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        clickRelayWriteBtn()
        clickQuitBtn()
    }

    private fun clickRelayWriteBtn() {
        binding.clDialogRelaywriteBackground.setOnClickListener() {
            Log.d("CustomDialogFragment", "다이얼로그 닫기 로직 필요!")
        }
    }

    private fun clickQuitBtn() {
        binding.clDialogQuitBackground.setOnClickListener() {
            Log.d("CustomDialogFragment", "home뷰로 가는 로직 필요!")
        }
    }

    companion object {
        fun newInstance(): CustomDialogFragment {
            return CustomDialogFragment()
        }
    }
}
