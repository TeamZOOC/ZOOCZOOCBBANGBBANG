package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {
    private val recordViewModel: RecordViewModel by viewModels()
    private var _binding: FragmentRecordBinding? = null
    private val binding get() = requireNotNull(_binding) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        clickImageBtn()
        clickNextBtn()
        return binding.root
    }

    // 사진을 불러오는 함수
    private fun clickImageBtn() {
        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.ivRecordBgimg.load(it) {
                transformations()
            }
        }
        binding.ivRecordImgbtn.setOnClickListener {
            launcher.launch("image/*")
        }
    }

    private fun clickNextBtn() {
        binding.btnRecordBottom.setOnClickListener() {
            Log.d("RecordFragment", "반려동물 선택 뷰로 이동하는 로직 필요!")
        }
    }

    companion object {
    }
}
