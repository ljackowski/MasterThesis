package net.jackowski.spring.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

//@Entity
class Credit(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int = 0,

    @Column(nullable = true, columnDefinition = "TEXT")
    var name: String? = "",

    @OneToOne
    @JoinColumn(name = "movie_credit_id", nullable = true)
    @JsonIgnore
    var stringMovieCredit: StringMovie? = null
) {
    override fun toString(): String {
        return "Credit(id=$id, name='$name')"
    }

    fun clearObject() {
        this.id = 0
        this.name = null
        this.stringMovieCredit = null
    }
}