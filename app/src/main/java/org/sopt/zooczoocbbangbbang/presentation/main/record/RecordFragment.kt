package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import coil.load
import org.sopt.zooczoocbbangbbang.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {
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

    companion object {
    }
}
