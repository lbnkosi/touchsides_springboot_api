package me.lbnkosi.touchsidesapi

object TextUtil {

    fun countWords(text: String): HashMap<String, Int> {
        val wordCounts = HashMap<String, Int>()
        text.split(Regex("\\s+")).forEach { word ->
            wordCounts[word] = wordCounts.getOrDefault(word, 0) + 1
        }
        return wordCounts
    }

    fun getMostFrequentWord(wordCounts: HashMap<String, Int>): String {
        var maxWord = ""
        var maxCount = 0
        for ((word, count) in wordCounts) {
            if (count > maxCount) {
                maxWord = word
                maxCount = count
            }
        }
        return maxWord
    }

    fun getMostFrequentSevenCharacterWord(wordCounts: HashMap<String, Int>): String {
        var maxWord = ""
        var maxCount = 0
        for ((word, count) in wordCounts) {
            if (word.length == 7 && count > maxCount) {
                maxWord = word
                maxCount = count
            }
        }
        return maxWord
    }

    fun getHighestScoringWords(wordCounts: HashMap<String, Int>): List<Pair<String, Int>> {
        val scores = HashMap<String, Int>()
        for ((word, count) in wordCounts) {
            val score = word.map { Const.SCORE_TABLE[it.toLowerCase().toString()] ?: 0 }.sum() * count
            scores[word] = score
        }
        val maxScore = scores.values.maxOrNull() ?: 0
        return scores.filter { it.value == maxScore }.toList()
    }

}