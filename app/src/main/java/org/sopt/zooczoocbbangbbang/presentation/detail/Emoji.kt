package org.sopt.zooczoocbbangbbang.presentation.detail

import org.sopt.zooczoocbbangbbang.R

enum class Emoji(val emojiId: Int, val viewId: Int) {
    DANCING(R.drawable.ic_emo_dancing, R.id.iv_dialog_emoji_dancing),
    HEART(R.drawable.ic_emo_heart, R.id.iv_dialog_emoji_heart),
    SMILE(R.drawable.ic_emo_smile, R.id.iv_dialog_emoji_smile),
    SAD(R.drawable.ic_emo_sad, R.id.iv_dialog_emoji_sad),
    THUMB(R.drawable.ic_emo_thumb, R.id.iv_dialog_emoji_thumb),
    SURPRISE(R.drawable.ic_emo_surprise, R.id.iv_dialog_emoji_surprise),
    HUG(R.drawable.ic_emo_hug, R.id.iv_dialog_emoji_hug),
    DOG(R.drawable.ic_emo_dog, R.id.iv_dialog_emoji_dog);

    companion object {
        const val ERROR = "[ERROR] EMOJI에 잘못된 ordinal 코드가 들어왔습니다."
        fun findIdByOrdinal(num: Int): Int {
            values().forEach {
                if (it.ordinal == num) {
                    return it.emojiId
                }
            }
            throw IllegalArgumentException(ERROR)
        }
    }
}
