package org.sopt.zooczoocbbangbbang.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.SimpleItemAnimator
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentHomeBinding
import org.sopt.zooczoocbbangbbang.domain.ArchivePostingData
import org.sopt.zooczoocbbangbbang.domain.PetData
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var petAdapter: PetAdapter
    private lateinit var archivePostingAdapter: ArchivePostingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPetAdapter()
        initArchiveAdapter()
        initList()
        initArchiveList()
    }

    private fun initPetAdapter() {
        petAdapter = PetAdapter()
        binding.rvHomePet.adapter = petAdapter
        val animator = binding.rvHomePet?.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    private fun initArchiveAdapter() {
        archivePostingAdapter = ArchivePostingAdapter()
        binding.rvArchivePosting.adapter = archivePostingAdapter
        val animator = binding.rvArchivePosting?.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    private fun initList() {
        val pets = listOf(
            PetData(R.drawable.ym1, "용민", true),
            PetData(R.drawable.ym2, "진수", false),
            PetData(R.drawable.ym3, "지영", false),
            PetData(R.drawable.ym4, "지은", false)
        )
        petAdapter.initPets(pets)
    }

    private fun initArchiveList() {
        val archives = listOf<ArchivePostingData>(
            ArchivePostingData(
                R.drawable.ym1,
                "진짜 한번에 됐으면 좋겠다 제발요 나 자고싶어 정말로",
                R.drawable.ym4,
                "용민",
                "01/06",
                listOf(ArchivePostingData.Commenter(R.drawable.ym2))
            ),
            ArchivePostingData(
                R.drawable.ym1,
                "동해물과 백두산이 마르고 닳도록 하나님이 보우하사 우리나라 만세 무궁화 삼천리 화려강산 대한 사람 대한으로 길이 보전하세",
                R.drawable.ym4,
                "진수",
                "01/07",
                listOf()
            ),
            ArchivePostingData(
                R.drawable.ym1,
                "알다시피 내 출신은 리 그걸 멀리보면 읍 그걸 멀리보면 시 그걸 멀리보면 도 그걸 멀리보면 나라 거를 멀리보면 원 Zoom Zoom Zoom 특별시를 향해 Zoom",
                R.drawable.ym4,
                "지영",
                "01/08",
                listOf()
            ),
            ArchivePostingData(
                R.drawable.ym1,
                "반쯤 미쳐가는 나 망가져 나의 삶 넌 내게서 나를 지워가고 또 나를 부셔버려라 추락한 나를 봐 눈앞에서 멀어지는 너의 속도는 400km로 너의 속도는 400km로 너의 속도는 400km로",
                R.drawable.ym4,
                "지은",
                "01/09",
                listOf(
                    ArchivePostingData.Commenter(R.drawable.ym2),
                    ArchivePostingData.Commenter(R.drawable.ym3)
                )
            ),
            ArchivePostingData(
                R.drawable.ym1,
                "달콤한 색감이 물들어 조금씩 정신을 차렸을 땐 알아볼 수도 없지 가득 찬 마음이 여물다 못해 터지고 있어 내일은 말을 걸어봐야지",
                R.drawable.ym4,
                "서우",
                "01/10",
                listOf()
            ),
            ArchivePostingData(
                R.drawable.ym1,
                "그 새끼보다 내가 못한 게 뭐야 도대체 왜 나는 가질 수 없는 거야 그 새끼는 너를 사랑하는 게 아냐 언제까지 바보같이 울고만 있을 거야",
                R.drawable.ym4,
                "준서",
                "01/11",
                listOf()
            ),
            ArchivePostingData(
                R.drawable.ym1,
                "I've been doing it all myself for a decade 넌 가둘려 했어 평생 날 너의 frame에 난 날 믿었고 평생 들어왔어 '왜 이래?' Just see me shining Hyperstar, that's me, baby",
                R.drawable.ym4,
                "효재",
                "01/12",
                listOf()
            ),
            ArchivePostingData(
                R.drawable.ym1,
                "밤은 깊었는데 잠은 안 오고 늘어난 두통과 싸우고 이리저리 뒤척이다 생각에 잠겨 또 펜을 붙잡고 빼곡히 써 내려가는 가사",
                R.drawable.ym4,
                "보미",
                "01/13",
                listOf(ArchivePostingData.Commenter(R.drawable.ym3))
            )
        )
        archivePostingAdapter.initArchives(archives)
    }
}
