package org.sopt.zooczoocbbangbbang.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.response.ResponseRecordDetailDto
import org.sopt.zooczoocbbangbbang.databinding.ActivityDetailBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity
import org.sopt.zooczoocbbangbbang.presentation.main.home.EmojiBottomSheetDialog
import org.sopt.zooczoocbbangbbang.util.ContextExt.shortToast

const val DETAIL_ERROR = "[ERROR] Detail Activity - 필요한 정보가 넘어오지 않았습니다."
const val IS_FIRST_RECORD = "첫 번째 게시물입니다"
const val IS_LAST_RECORD = "마지막 게시물입니다"

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private lateinit var commentAdapter: CommentAdapter
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getDataFromHome()
        initAdapter()
        //initImages()
        initComments()
        clickEmojiSelection()
        clickUploadButton()
        uploadEmoji()
    }

    override fun onResume() {
        super.onResume()
        //getRecordDetail()
    }

    private fun getDataFromHome() {
        val bundle = intent.extras
        Log.d("asdf", "detailView: content: ${bundle?.getString(CONTENT)}")
        binding.ivDetailImage.load(bundle?.getString(PET_IMAGE))
        binding.ivDetailEditorImage.load(bundle?.getString(WRITER_IMAGE))
        binding.data = ResponseRecordDetailDto.RecordDetailDto.Record(
            content = bundle?.getString(CONTENT) ?: "",
            date = bundle?.getString(DATE) ?: "",
            id = -1,
            photo = "",
            writerName = bundle?.getString(WRITER_NAME) ?: "",
            writerPhoto = ""
        )
    }

    private fun getRecordDetail() {
        detailViewModel.getDetailData()
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
