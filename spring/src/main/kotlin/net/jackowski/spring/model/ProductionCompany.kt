package net.jackowski.spring.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

//@Entity
class ProductionCompany(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int = 0,

    @Column(nullable = true, columnDefinition = "TEXT")
    var name: String? = "",

    @OneToOne
    @JoinColumn(name = "movie_production_company_id", nullable = true)
    @JsonIgnore
    var movieProductionCompany: Movie? = null
) {
    override fun toString(): String {
        return "ProductionCompany(id=$id, name='$name')"
    }

    fun clearObject() {
        this.id = 0
        this.name = null
        this.movieProductionCompany = null
    }
}