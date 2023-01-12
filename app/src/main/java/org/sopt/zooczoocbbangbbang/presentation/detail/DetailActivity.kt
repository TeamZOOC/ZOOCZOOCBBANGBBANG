package org.sopt.zooczoocbbangbbang.presentation.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    private var recordId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataFromHome()
        initAdapter()
        uploadEmoji()
        gatherClickEvents()
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            getRecordDetail(recordId)
        }, 300L)
        gatherInitData()
    }

    private fun gatherInitData() {
        initTextData()
        initImages()
        initComments()
    }

    private fun gatherClickEvents() {
        clickLeftArrow()
        clickRightArrow()
        clickEmojiSelection()
        clickUploadButton()
        clickCloseButton()
    }

    private fun clickLeftArrow() {
        binding.ivDetailLeft.setOnClickListener {
            selectDirection(Direction.LEFT)
        }
    }

    private fun clickRightArrow() {
        binding.ivDetailRight.setOnClickListener {
            selectDirection(Direction.RIGHT)
        }
    }

    private fun clickEmojiSelection() {
        binding.ivDetailEmoji.setOnClickListener {
            val emojiBottomSheet = EmojiBottomSheetDialog()
            emojiBottomSheet.show(supportFragmentManager, emojiBottomSheet.tag)
        }
    }

    private fun clickUploadButton() {
        binding.ivDetailUpload.setOnClickListener {
            uploadComment()
            binding.etDetailInput.text.clear()
        }
    }

    private fun clickCloseButton() {
        binding.ivDetailClose.setOnClickListener {
            finishAfterTransition()
        }
    }

    private fun getDataFromHome() {
        val myBundle = intent.extras
        val data = ResponseRecordDetailDto.RecordDetailDto.Record(
            content = myBundle?.getString(CONTENT) ?: throw IllegalArgumentException(DETAIL_ERROR),
            date = myBundle.getString(DATE) ?: throw IllegalArgumentException(DETAIL_ERROR),
            id = myBundle.getInt(ID),
            photo = myBundle.getString(PET_IMAGE) ?: throw IllegalArgumentException(DETAIL_ERROR),
            writerName = myBundle.getString(WRITER_NAME) ?: throw IllegalArgumentException(
                DETAIL_ERROR
            ),
            writerPhoto = myBundle.getString(WRITER_IMAGE)
        )
        binding.ivDetailImage.load(data.photo)
        binding.ivDetailEditorImage.load(data.writerPhoto ?: R.drawable.ic_default_image)
        binding.data = data
        recordId = data.id
    }

    private fun selectDirection(direction: Direction) {
        val id: Int?
        val message: String
        when (direction) {
            Direction.LEFT -> {
                id = detailViewModel.recordDetail.value?.leftId
                message = IS_FIRST_RECORD
            }
            Direction.RIGHT -> {
                id = detailViewModel.recordDetail.value?.rightId
                message = IS_LAST_RECORD
            }
        }
        goToSideRecord(id, message)
    }

    private fun goToSideRecord(id: Int?, message: String) {
        if (id == null) {
            shortToast(message)
            return
        }
        getRecordDetail(id)
    }

    private fun getRecordDetail(recordId: Int) {
        detailViewModel.getDetailData(recordId)
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
            binding.ivDetailEditorImage.load(it.record.writerPhoto ?: R.drawable.ic_default_image)
        }
    }

    private fun initTextData() {
        detailViewModel.recordDetail.observe(this) {
            binding.data = it.record
        }
    }

    private fun uploadComment() {
        val comment = binding.etDetailInput.text.toString()
        detailViewModel.uploadComment(
            detailViewModel.recordDetail.value?.record?.id ?: error(""),
            comment
        )
    }

    private fun uploadEmoji() {
        detailViewModel.emojiId.observe(this) {
            detailViewModel.uploadEmoji(
                detailViewModel.recordDetail.value?.record?.id ?: error(""),
                it
            )
        }
    }

    companion object {
        const val ID = "id"
        const val PET_IMAGE = "petImage"
        const val DATE = "date"
        const val WRITER_IMAGE = "writerProfile"
        const val WRITER_NAME = "writerName"
        const val CONTENT = "content"
    }
}
