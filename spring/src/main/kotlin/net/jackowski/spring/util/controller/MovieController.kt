package net.jackowski.spring.util.controller

import net.jackowski.spring.util.service.MovieService
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController(val movieService: MovieService) {
}