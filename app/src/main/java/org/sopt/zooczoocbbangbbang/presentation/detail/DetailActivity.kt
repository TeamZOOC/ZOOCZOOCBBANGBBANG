package org.sopt.zooczoocbbangbbang.presentation.detail

import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityDetailBinding
import org.sopt.zooczoocbbangbbang.domain.detail.Comment
import org.sopt.zooczoocbbangbbang.domain.detail.PostingContent
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity
import org.sopt.zooczoocbbangbbang.presentation.main.home.EmojiBottomSheetDialog
import org.sopt.zooczoocbbangbbang.util.ContextExt.shortToast

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private lateinit var commentAdapter: CommentAdapter
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = detailViewModel

        initAdapter()
        initTempData()
        initImages()
        initComments()
        clickEmojiSelection()
        clickUploadButton()
        uploadEmoji()
    }

    private fun clickEmojiSelection() {
        binding.ivDetailEmoji.setOnClickListener {
            val emojiBottomSheet = EmojiBottomSheetDialog()
            emojiBottomSheet.show(supportFragmentManager, emojiBottomSheet.tag)
        }
    }

    private fun initAdapter() {
        commentAdapter = CommentAdapter()
        binding.rvDetailComment.adapter = commentAdapter
    }

    private fun initComments() {
        detailViewModel.comments.observe(this) {
            commentAdapter.initComments(it)
        }
    }

    private fun initImages() {
        detailViewModel.postingContent.observe(this) {
            binding.ivDetailImage.load(it.image)
            binding.ivDetailEditorImage.load(it.editor.image)
        }
    }

    private fun clickUploadButton() {
        binding.ivDetailUpload.setOnClickListener {
            uploadComment()
        }
    }

    private fun uploadComment() {
        val comment = binding.etDetailInput.text.toString()
        detailViewModel.uploadComment(comment)
    }

    private fun uploadEmoji() {
        detailViewModel.emojiId.observe(this) {
            shortToast("눌린 id는 ${it}번입니다.")
            detailViewModel.uploadEmoji(it)
        }
    }

    private fun initTempData() {
        detailViewModel.postingContent.value = PostingContent(
            image = R.drawable.junseo1,
            date = "1월 9일",
            content = "오늘 복실이 사진 찍었다\n다들 댓글 남겨줘!",
            previousId = 0,
            nextId = 2,
            editor = PostingContent.Editor(
                image = R.drawable.ym2,
                name = "권용민"
            )
        )

        detailViewModel.comments.value = listOf(
            Comment(
                isEmoji = false,
                emojiOrdinal = -1,
                profileImage = R.drawable.bomi2,
                editor = "보미",
                content = "오~ 멋진데?",
                date = "1월 10일"
            ),
            Comment(
                isEmoji = true,
                emojiOrdinal = 1,
                profileImage = R.drawable.ym4,
                editor = "영맨",
                content = "오~ 예쁜데?",
                date = "1월 10일"
            )
        )
    }
}
