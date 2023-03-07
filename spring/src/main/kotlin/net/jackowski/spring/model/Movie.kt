package net.jackowski.spring.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import java.time.LocalDate

@Entity
class Movie(
    @Id
    var movieId: String = "",

    @Column(nullable = true, columnDefinition = "TEXT")
    var title: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var originalLanguage: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var overview: String? = null,

    @Column(nullable = true)
    var popularity: Double? = null,

    @Column(nullable = true)
    var releaseDate: LocalDate? = null,

    @Column(nullable = true)
    var budget: Double? = null,

    @Column(nullable = true)
    var revenue: Double? = null,

    @Column(nullable = true)
    var runtime: Double? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var status: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var tagline: String? = null,

    @Column(nullable = true)
    var voteAverage: Double? = null,

    @Column(nullable = true)
    var voteCount: Double? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var posterPath: String? = null,

    @Column(nullable = true, columnDefinition = "TEXT")
    var backdropPath: String? = null,

    @OneToOne(mappedBy = "movieProductionCompany", cascade = [CascadeType.ALL])
    var productionCompanies: ProductionCompany? = ProductionCompany(),

    @OneToOne(mappedBy = "movieCredit", cascade = [CascadeType.ALL])
    var credits: Credit? = Credit(),

    @OneToOne(mappedBy = "movieKeyword", cascade = [CascadeType.ALL])
    var keywords: Keyword? = Keyword(),

    @OneToOne(mappedBy = "movieGenre", cascade = [CascadeType.ALL])
    var genres: Genre? = Genre(),

    @OneToOne(mappedBy = "movieRecommendation", cascade = [CascadeType.ALL])
    var recommendations: Recommendation? = Recommendation()
) {
    override fun toString(): String {
        return "Movie(movieId='$movieId', title='$title', originalLanguage='$originalLanguage', overview='$overview', popularity=$popularity, releaseDate='$releaseDate', budget=$budget, revenue=$revenue, runtime=$runtime, status='$status', tagline='$tagline', voteAverage=$voteAverage, voteCount=$voteCount, posterPath='$posterPath', backdropPath='$backdropPath', productionCompanies=$productionCompanies, credits=$credits, keywords=$keywords, genres=$genres, recommendations=$recommendations)"
    }

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
        this.productionCompanies?.clearObject()
        this.credits?.clearObject()
        this.keywords?.clearObject()
        this.genres?.clearObject()
        this.recommendations?.clearObject()
    }
}