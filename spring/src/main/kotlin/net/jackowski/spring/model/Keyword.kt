package net.jackowski.spring.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

//@Entity
class Keyword(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int = 0,

    @Column(nullable = true, columnDefinition = "TEXT")
    var name: String? = "",

    @OneToOne
    @JoinColumn(name = "movie_keyword_id", nullable = true)
    @JsonIgnore
    var movieKeyword: Movie? = null
) {
    override fun toString(): String {
        return "Keyword(id=$id, name='$name')"
    }

    fun clearObject() {
        this.id = 0
        this.name = null
        this.movieKeyword = null
    }
}