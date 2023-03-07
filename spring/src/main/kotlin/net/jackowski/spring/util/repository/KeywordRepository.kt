package net.jackowski.spring.util.repository

import net.jackowski.spring.model.Keyword
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KeywordRepository : JpaRepository<Keyword, Short> {
    fun findAllByMovieKeywordMovieId(movieId: String): List<Keyword>
}