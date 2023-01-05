package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.zooczoocbbangbbang.databinding.FragmentChoose2PetBinding

class Choose2PetFragment : Fragment() {
    private var _binding: FragmentChoose2PetBinding? = null
    private val binding get() = requireNotNull(_binding) { "바인딩 객체 생성하고 쓰기" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoose2PetBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }
}
