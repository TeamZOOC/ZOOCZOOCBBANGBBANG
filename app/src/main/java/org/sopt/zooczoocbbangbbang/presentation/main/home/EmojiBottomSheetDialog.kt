package org.sopt.zooczoocbbangbbang.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.BottomSheetDialogEmojiBinding
import org.sopt.zooczoocbbangbbang.presentation.detail.DetailViewModel
import org.sopt.zooczoocbbangbbang.presentation.detail.Emoji

class EmojiBottomSheetDialog : BottomSheetDialogFragment() {
    private var _binding: BottomSheetDialogEmojiBinding? = null
    private val binding: BottomSheetDialogEmojiBinding get() = requireNotNull(_binding) { "${this::class.java.simpleName}에서 바인딩 초기화 에러가 발생했습니다." }
    private val detailViewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_dialog_emoji, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEmoji()
    }

    private fun clickEmoji() {
        Emoji.values().forEach { emoji ->
            (view?.findViewById<ImageView>(emoji.viewId))?.setOnClickListener {
                detailViewModel.emojiId.value = emoji.ordinal
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
