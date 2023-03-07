package net.jackowski.spring.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class Genre(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Short = 0,

    @Column(nullable = true, columnDefinition = "TEXT")
    var name: String? = "",

    @OneToOne
    @JoinColumn(name = "movie_genre_id", nullable = true)
    @JsonIgnore
    var movieGenre: Movie? = null
) {
    override fun toString(): String {
        return "Genre(id=$id, name='$name')"
    }

    fun clearObject() {
        this.id = 0
        this.name = null
        this.movieGenre = null
    }
}