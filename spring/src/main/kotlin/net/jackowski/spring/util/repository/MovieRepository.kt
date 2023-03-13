package net.jackowski.spring.util.repository

import net.jackowski.spring.model.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<Movie, String> {
}