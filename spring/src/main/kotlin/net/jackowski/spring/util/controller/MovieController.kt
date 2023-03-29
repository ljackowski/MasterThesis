package net.jackowski.spring.util.controller

import net.jackowski.spring.model.TestResult
import net.jackowski.spring.util.service.MovieService
import org.springframework.web.bind.annotation.*
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@RestController
@CrossOrigin(origins = ["http://localhost:3000/", "http://localhost:5000/"])
@RequestMapping("spring-api/")
class MovieController(private val movieService: MovieService) {
    @GetMapping("load")
    fun loadMoviesToDatabase() {
        movieService.loadMoviesToDatabase()
    }

    @GetMapping("get/algorithm")
    fun checkAlgorithm(@RequestParam patternToSearch: String, @RequestParam algorithmType: String): TestResult {
        val start = Instant.now()
        val result = movieService.performAlgorithm(patternToSearch, algorithmType)
        val stop = Instant.now()
        val difference = Duration.between(start, stop).toMillis()
        return TestResult(
            resultList = result,
            testStartDate = LocalDateTime.ofInstant(start, ZoneId.of("Europe/Warsaw")).toString(),
            testStopDate = LocalDateTime.ofInstant(stop, ZoneId.of("Europe/Warsaw")).toString(),
            durationInMilli = difference
        )
    }

    @GetMapping("get")
    fun getTest(@RequestParam query: String): TestResult {
        val start = Instant.now()
        val result = movieService.getFromQuery(query)
        val stop = Instant.now()
        val difference = Duration.between(start, stop).toMillis()
        return TestResult(
            testStartDate = LocalDateTime.ofInstant(start, ZoneId.of("Europe/Warsaw")).toString(),
            testStopDate = LocalDateTime.ofInstant(stop, ZoneId.of("Europe/Warsaw")).toString(),
            durationInMilli = difference,
            movies = result
        )
    }

    @PostMapping("operate")
    fun operateOnMovie(@RequestParam query: String): TestResult {
        val start = Instant.now()
        val result = movieService.operateOnMovie(query)
        val stop = Instant.now()
        val difference = Duration.between(start, stop).toMillis()
        return TestResult(
            testStartDate = LocalDateTime.ofInstant(start, ZoneId.of("Europe/Warsaw")).toString(),
            testStopDate = LocalDateTime.ofInstant(stop, ZoneId.of("Europe/Warsaw")).toString(),
            durationInMilli = difference,
            operationResult = result
        )
    }

}