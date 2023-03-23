package net.jackowski.spring.util.controller

import net.jackowski.spring.model.Movie
import net.jackowski.spring.util.service.MovieService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:3000/"])
@RequestMapping("spring-api/")
class MovieController(private val movieService: MovieService) {
    @GetMapping("load")
    fun loadMoviesToDatabase() {
        movieService.loadMoviesToDatabase()
    }

    @GetMapping("get/algorithm")
    fun checkAlgorithm(@RequestParam patternToSearch: String, @RequestParam algorithmType: String): List<String>{
        return movieService.performAlgorithm(patternToSearch, algorithmType)
    }

    @GetMapping("get")
    fun getTest(@RequestParam query: String): List<Movie> {
        return movieService.getFromQuery(query)
    }

    @PostMapping("operate")
    fun operateOnMovie(@RequestParam query: String): Int{
        return movieService.operateOnMovie(query)
    }

}