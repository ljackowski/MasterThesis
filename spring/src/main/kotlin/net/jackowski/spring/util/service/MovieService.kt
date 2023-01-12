package net.jackowski.spring.util.service

import net.jackowski.spring.util.repository.MovieRepository
import org.springframework.stereotype.Service

@Service
class MovieService(val movieRepository: MovieRepository) {
}