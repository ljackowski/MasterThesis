package net.jackowski.spring.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity(name = "movies")
@Table(name = "movies")
class StringMovie(
    @Id
    var movieId: String = "",

    @Column(nullable = true, columnDefinition = "TEXT")
    var title: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var originalLanguage: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var overview: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var popularity: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var releaseDate: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var budget: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var revenue: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var runtime: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var status: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var tagline: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var voteAverage: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var voteCount: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var posterPath: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var backdropPath: String? = null,

    //@OneToOne(mappedBy = "movieProductionCompany", cascade = [CascadeType.ALL])
    @Column(nullable = true, columnDefinition = "TEXT")
    var productionCompanies: String? = null,

    //@OneToOne(mappedBy = "movieCredit", cascade = [CascadeType.ALL])
    @Column(nullable = true, columnDefinition = "TEXT")
    var credits: String? = null,

    //@OneToOne(mappedBy = "movieKeyword", cascade = [CascadeType.ALL])
    @Column(nullable = true, columnDefinition = "TEXT")
    var keywords: String? = null,

    //@OneToOne(mappedBy = "movieGenre", cascade = [CascadeType.ALL])
    @Column(nullable = true, columnDefinition = "TEXT")
    var genres: String? = null,

    //@OneToOne(mappedBy = "movieRecommendation", cascade = [CascadeType.ALL])
    @Column(nullable = true, columnDefinition = "TEXT")
    var recommendations: String? = ""
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