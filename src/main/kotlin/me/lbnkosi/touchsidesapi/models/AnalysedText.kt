package me.lbnkosi.touchsidesapi.models

data class AnalysedText(
    var link: String? = "",

    var mostFrequentWord: String? = "",
    var mostFrequentWordCount: Int? = 0,

    var mostFrequent7CharWord: String? = "",
    var mostFrequent7CharWordCount: Int? = 0,

    var highestScoringWord: String? = "",
    var highestScoringWordScore: Int? = 0,

    var averageScore: Double? = 0.0
)
