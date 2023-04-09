package net.jackowski.spring.model

import java.time.LocalDate

data class Movie(
    var movieId: String = "",
    var title: String? = null,
    var originalLanguage: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var releaseDate: LocalDate? = null,
    var budget: Double? = null,
    var revenue: Double? = null,
    var runtime: Double? = null,
    var status: String? = null,
    var tagline: String? = null,
    var voteAverage: Double? = null,
    var voteCount: Double? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null,
    var productionCompanies: List<String>? = null,
    var credits: List<String>? = null,
    var keywords: List<String>? = null,
    var genres: List<String>? = null,
    var recommendations: List<String>? = null
) {
    fun clearObject() {
        this.movieId = ""
        this.title = null
        this.originalLanguage = null
        this.overview = null
        this.popularity = null
        this.releaseDate = null
        this.budget = null
        this.revenue = null
        this.runtime = null
        this.status = null
        this.tagline = null
        this.voteAverage = null
        this.voteCount = null
        this.posterPath = null
        this.backdropPath = null
        this.productionCompanies = null
        this.credits = null
        this.keywords = null
        this.genres = null
        this.recommendations = null
    }

}