package net.jackowski.spring.model

class TestResult(
    var testStartDate: String = "",
    var testStopDate: String = "",
    var durationInMilli: Long = 0L,
    var operationResult: Int = 0,
    var resultList: List<String> = ArrayList(),
    var movies: List<Movie> = ArrayList()
)