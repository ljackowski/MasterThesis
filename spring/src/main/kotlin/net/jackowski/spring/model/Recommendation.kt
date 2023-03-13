package net.jackowski.spring.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

//@Entity
class Recommendation(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int = 0,

    @Column(nullable = true, columnDefinition = "TEXT")
    var name: String? = "",

    @OneToOne
    @JoinColumn(name = "movie_recommendation_id", nullable = true)
    @JsonIgnore
    var movieRecommendation: Movie? = null
) {
    override fun toString(): String {
        return "Recommendation(id=$id, name='$name')"
    }

    fun clearObject() {
        this.id = 0
        this.name = null
        this.movieRecommendation = null
    }
}