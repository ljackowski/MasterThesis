package net.jackowski.spring.util.controller

import net.jackowski.spring.model.Keyword
import net.jackowski.spring.util.repository.KeywordRepository
import net.jackowski.spring.util.service.MovieService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("spring-api/")
class MovieController(private val movieService: MovieService, private val keywordRepository: KeywordRepository) {

    @GetMapping("load")
    fun loadMoviesToDatabase(){
        movieService.loadMoviesToDatabase()
    }

    @GetMapping("test/{id}")
    fun test(@PathVariable id: String): List<Keyword>{
        return keywordRepository.findAllByMovieKeywordMovieId(id)
    }
}