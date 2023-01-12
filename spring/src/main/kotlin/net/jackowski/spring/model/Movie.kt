package net.jackowski.spring.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Movie(
    @Id
    var movieId: String = "",
    var title: String = "",
    var originalLanguage: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var releaseDate: String = "",
    var budget: Double = 0.0,
    var revenue: Double = 0.0,
    var runtime: Double = 0.0,
    var status: String = "",
    var tagline: String = "",
    var voteAverage: Double = 0.0,
    var voteCount: Double = 0.0,
    var posterPath: String = "",
    var backdropPath: String = "",

/*    var productionCompanies: List<ProductionCompany>? = null,
    var credits: List<Credit>? = null,
    var keywords: List<Keyword>? = null,
    var genres: List<Genre>? = null,
    var recommendations: List<Recommendation>? = null,*/
)