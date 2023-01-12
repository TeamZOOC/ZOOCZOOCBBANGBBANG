package org.sopt.zooczoocbbangbbang.presentation.main.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponseTotalRecordsDto
import org.sopt.zooczoocbbangbbang.databinding.ItemGridArchivePostingBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemLinearArchivePostingBinding
import org.sopt.zooczoocbbangbbang.presentation.detail.DetailActivity.Companion.CONTENT
import org.sopt.zooczoocbbangbbang.presentation.detail.DetailActivity.Companion.DATE
import org.sopt.zooczoocbbangbbang.presentation.detail.DetailActivity.Companion.PET_IMAGE
import org.sopt.zooczoocbbangbbang.presentation.detail.DetailActivity.Companion.WRITER_IMAGE
import org.sopt.zooczoocbbangbbang.presentation.detail.DetailActivity.Companion.WRITER_NAME
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.ArchivePostingData
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.RecordTransportData

abstract class ArchivePostingViewHolder(private val binding: View) :
    RecyclerView.ViewHolder(binding) {
    abstract fun onBind(
        data: ArchivePostingData,
        clickItem: () -> Unit,
        clickExpandedItem: (views: Map<String, View>, recordTransportData: RecordTransportData) -> Unit
    )
}

class ArchivePostingLinearViewHolder(private val binding: ItemLinearArchivePostingBinding) :
    ArchivePostingViewHolder(binding.root) {

    private lateinit var commentersAdapter: CommentersAdapter

    override fun onBind(
        data: ArchivePostingData,
        clickItem: () -> Unit,
        clickExpandedItem: (views: Map<String, View>, recordTransportData: RecordTransportData) -> Unit
    ) {
        binding.ivArchivePetImage.load(data.record.photo)
        binding.ivArchiveUploaderProfile.load(data.record.writerPhoto)
        binding.data = data
        initAdapter(data.commentWriters)

        if (data.isSelected) {
            expandItem()
        } else {
            foldItem()
        }

        itemView.setOnClickListener {
            if (data.isSelected) {
                clickExpandedItem(
                    mapOf(
                        PET_IMAGE to binding.ivArchivePetImage,
                        DATE to binding.tvArchiveDate,
                        WRITER_IMAGE to binding.ivArchiveUploaderProfile,
                        WRITER_NAME to binding.tvArchiveEditor,
                        CONTENT to binding.tvArchiveContent
                    ),
                    RecordTransportData(
                        petImage = data.record.photo,
                        date = data.record.date,
                        writerImage = data.record.writerPhoto,
                        writerName = data.record.writerName,
                        content = data.record.content
                    )
                )
            } else {
                clickItem()
            }
        }
    }

    private fun initAdapter(commenters: List<ResponseTotalRecordsDto.RecordDto.CommentWriter>) {
        commentersAdapter = CommentersAdapter(commenters)
        binding.rvCommenters.adapter = commentersAdapter
    }

    private fun expandItem() {
        binding.mlArchivePosting.transitionToEnd()
    }

    private fun foldItem() {
        binding.mlArchivePosting.transitionToStart()
    }
}

class ArchivePostingGridViewHolder(private val binding: ItemGridArchivePostingBinding) :
    ArchivePostingViewHolder(binding.root) {
    override fun onBind(
        data: ArchivePostingData,
        clickItem: () -> Unit,
        clickExpandedItem: (views: Map<String, View>, recordTransportData: RecordTransportData) -> Unit
    ) {
        binding.ivArchiveGridPetImage.load(data.record.photo)
        itemView.setOnClickListener {
            clickExpandedItem(
                mapOf(PET_IMAGE to binding.ivArchiveGridPetImage),
                RecordTransportData(
                    petImage = data.record.photo,
                    "",
                    "",
                    "",
                    ""
                )
            )
        }
    }
}
