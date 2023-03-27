package me.lbnkosi.touchsidesapi.controller

import me.lbnkosi.touchsidesapi.Const.SCORE_TABLE
import me.lbnkosi.touchsidesapi.TextUtil.countWords
import me.lbnkosi.touchsidesapi.TextUtil.getHighestScoringWords
import me.lbnkosi.touchsidesapi.TextUtil.getMostFrequentSevenCharacterWord
import me.lbnkosi.touchsidesapi.TextUtil.getMostFrequentWord
import me.lbnkosi.touchsidesapi.models.AnalysedText
import me.lbnkosi.touchsidesapi.models.wrapper.Error
import me.lbnkosi.touchsidesapi.models.wrapper.ResponseWrapper
import me.lbnkosi.touchsidesapi.models.wrapper.Success
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URL
import kotlin.math.roundToInt
import me.lbnkosi.touchsidesapi.models.RequestBody as RequestBody1

@RestController
@RequestMapping("/api/v1/analyse")
class TextAnalyserController {

    var wordCounts: HashMap<String, Int> = hashMapOf()

    var highestScoringWords: List<Pair<String, Int>> = listOf()

    @PostMapping
    fun analyseText(@RequestBody body: RequestBody1): ResponseEntity<ResponseWrapper> {
        if (!body.link.isNullOrEmpty()) {
            val newText = getTextFromUrl(body.link!!)
            wordCounts = countWords(newText)
            highestScoringWords = getHighestScoringWords(wordCounts)
            val mostFrequentWord2 = getMostFrequentWord(wordCounts)
            val mostFrequentSevenCharWord2 = getMostFrequentSevenCharacterWord(wordCounts)
            val averageScore2 = wordCounts.map { (word, count) -> count * SCORE_TABLE.getOrDefault(word, 0) }.sum().toDouble() / wordCounts.values.sum()
            val analysedText: AnalysedText = AnalysedText().apply {
                link = body.link
                mostFrequentWord = mostFrequentWord2
                mostFrequentWordCount = wordCounts[mostFrequentWord2]
                mostFrequent7CharWord = mostFrequentSevenCharWord2
                mostFrequent7CharWordCount = wordCounts[mostFrequentSevenCharWord2]
                highestScoringWord = highestScoringWords.first().first
                highestScoringWordScore = highestScoringWords.first().second
                averageScore = (averageScore2 * 100).roundToInt() / 100.0
            }
            return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper(status = "2", error = null, success = Success(message = ""), result = analysedText))
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseWrapper(status = "1", error = Error(error = "", errorCode = 0, message = ""), success = null, result = null))
        }
    }

    fun getTextFromUrl(urlString: String): String {
        return try {
            URL(urlString).readText().toLowerCase()
        } catch (e: Exception) {
            ""
        }
    }

}