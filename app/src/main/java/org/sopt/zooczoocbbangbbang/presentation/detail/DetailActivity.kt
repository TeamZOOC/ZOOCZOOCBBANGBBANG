package org.sopt.zooczoocbbangbbang.presentation.detail

import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityDetailBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity
import org.sopt.zooczoocbbangbbang.presentation.main.home.EmojiBottomSheetDialog
import org.sopt.zooczoocbbangbbang.util.ContextExt.shortToast

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private lateinit var commentAdapter: CommentAdapter
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = detailViewModel

        getRecordDetail()
        initAdapter()
        initImages()
        initComments()
        clickEmojiSelection()
        clickUploadButton()
        uploadEmoji()
    }

    private fun getRecordDetail() {
        //detailViewModel.getDetailData()
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
        detailViewModel.recordDetail.observe(this) {
            binding.ivDetailImage.load(it.record.photo)
            binding.ivDetailEditorImage.load(it.record.writerPhoto)
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

    companion object {
        const val PET_IMAGE = "petImage"
        const val DATE = "date"
        const val WRITER_IMAGE = "writerProfile"
        const val WRITER_NAME = "writerName"
        const val CONTENT = "content"
    }
}
