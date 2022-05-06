package com.example.searchstationapplication.common

object SoundSearcher {
    private const val HANGUL_BEGIN_UNICODE = 44032.toChar()
    private const val HANGUL_LAST_UNICODE = 55203.toChar()
    private const val HANGUL_BASE_UNIT = 588.toChar()
    private val INITIAL_SOUND = charArrayOf(
        'ㄱ',
        'ㄲ',
        'ㄴ',
        'ㄷ',
        'ㄸ',
        'ㄹ',
        'ㅁ',
        'ㅂ',
        'ㅃ',
        'ㅅ',
        'ㅆ',
        'ㅇ',
        'ㅈ',
        'ㅉ',
        'ㅊ',
        'ㅋ',
        'ㅌ',
        'ㅍ',
        'ㅎ'
    )

    private fun isInitialSound(searchar: Char): Boolean {
        for (c in INITIAL_SOUND) {
            if (c == searchar) {
                return true
            }
        }
        return false
    }

    private fun getInitialSound(c: Char): Char {
        val hanBegin = c - HANGUL_BEGIN_UNICODE
        val index = hanBegin / HANGUL_BASE_UNIT.code
        return INITIAL_SOUND[index]
    }

    private fun isHangul(c: Char): Boolean {
        return c in HANGUL_BEGIN_UNICODE..HANGUL_LAST_UNICODE
    }

    fun matchString(value: String, search: String): Boolean {
        var t: Int
        val seof = value.length - search.length
        val slen = search.length
        if (seof < 0) return false
        for (i in 0..seof) {
            t = 0
            while (t < slen) {
                if (isInitialSound(search[t]) && isHangul(value[i + t])) {
                    if (getInitialSound(value[i + t]) == search[t])
                        t++ else break
                } else {

                    if (value[i + t] == search[t])
                        t++ else break
                }
            }
            if (t == slen) return true
        }
        return false
    }
}