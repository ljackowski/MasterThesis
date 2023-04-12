package net.jackowski.spring.util.controller

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.jackowski.spring.model.Movie
import net.jackowski.spring.model.StringMovie
import net.jackowski.spring.model.TestResult
import net.jackowski.spring.util.service.MovieService
import org.springframework.web.bind.annotation.*
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@RestController
@CrossOrigin(origins = ["*"])
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
        val result = movieService.getFromQuery(query)
        val start = Instant.now()
        val movies: MutableList<Movie> = ArrayList()
        result.forEach {
            movies.add(movieService.convertStringMovieToMovie(it))
        }
        val stop = Instant.now()
        val difference = Duration.between(start, stop).toMillis()
        return TestResult(
            testStartDate = LocalDateTime.ofInstant(start, ZoneId.of("Europe/Warsaw")).toString(),
            testStopDate = LocalDateTime.ofInstant(stop, ZoneId.of("Europe/Warsaw")).toString(),
            durationInMilli = difference,
            stringMovies = result,
            movies = movies
        )
    }

    @CrossOrigin(origins = ["*"])
    @PostMapping("operate")
    fun operateOnMovie(@RequestParam operationType: String, @RequestParam stringMovies: String): TestResult {
        val start = Instant.now()
        val tokenType = object : TypeToken<List<StringMovie>>() {}.type
        val result = movieService.operateOnMovie(operationType, Gson().fromJson(stringMovies, tokenType))
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