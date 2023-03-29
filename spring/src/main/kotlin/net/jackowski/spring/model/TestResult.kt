package net.jackowski.spring.model

class TestResult(
    var testStartDate: String = "",
    var testStopDate: String = "",
    var durationInMilli: Long = 0L,
    var operationResult: Boolean = false,
    var resultList: List<String> = ArrayList(),
    var stringMovies: List<StringMovie> = ArrayList(),
    var movies: List<Movie> = ArrayList()
)