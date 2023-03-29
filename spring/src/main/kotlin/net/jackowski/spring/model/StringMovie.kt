package net.jackowski.spring.model

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
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
        this.productionCompanies = null
        this.credits = null
        this.keywords = null
        this.genres = null
        this.recommendations = null
        /*        this.productionCompanies?.clearObject()
                this.credits?.clearObject()
                this.keywords?.clearObject()
                this.genres?.clearObject()
                this.recommendations?.clearObject()*/
    }
}

fun main() {
    val jsonstring =
        "{ \"movieId\": \"135397\", \"title\": \"Jurassic World\", \"originalLanguage\": \"en\", \"overview\": \"Twenty-two years after the events of Jurassic Park Isla Nublar now features a fully functioning dinosaur theme park Jurassic World as originally envisioned by John Hammond.\", \"popularity\": \"99.327\", \"releaseDate\": \"2015-06-12\", \"budget\": \"150000000.0\", \"revenue\": \"1671537444.0\", \"runtime\": \"124.0\", \"status\": \"Released\", \"tagline\": \"The park is open.\", \"voteAverage\": \"6.678\", \"voteCount\": \"18835.0\", \"posterPath\": \"/A0LZHXUzo5C60Oahvt7VxvwuzHw.jpg\", \"backdropPath\": \"/aIGIYJTyOkEVUmEd3z5x6diYsFx.jpg\", \"productionCompanies\": \"Universal Pictures-Amblin Entertainment\", \"credits\": \"Chris Pratt-Bryce Dallas Howard-Ty Simpkins-Nick Robinson-Vincent D'Onofrio-BD Wong-Omar Sy-Jake Johnson-Irrfan Khan-Judy Greer-Lauren Lapkus-Brian Tee-Katie McGrath-Andy Buckley-Eric Edelstein-Courtney J. Clark-Colby Boothman-Jimmy Fallon-James DuMont-Matt Burke-Anna Talakkottur-Matthew Cardarople-Michael Papajohn-William Gary Smith-Kelly Washington-Isaac Keys-Patrick Crowley-Chad Randall-Gary Weeks-Bill Ogilvie-Allan Tam-Yvonne Angulo-Chloe Perrin-Timothy Eulich-Kevin Foster-Bonnie Wild-Brad Bird-Colin Trevorrow-Justin Lacalamita-Tiffany Forest-Arlene Newman-Tait Fletcher-Jimmy Buffett-Tim Connolly-Johnny Otto-Erika Erica-Brandon Marc Higa-Martin Klebba-Eddie J. Fernandez\", \"keywords\": \"dna-tyrannosaurus rex-velociraptor-island-animal attack-primal fear-sequel-disaster-escape-dinosaur-creature-park-amusement park-theme park-genetic engineering-raptor-animal horror\", \"genres\": \"Action-Adventure-Science Fiction-Thriller\", \"recommendations\": \"351286-331-329-99861-330-76341-102899-118340-87101-140607-131631-198663-293660-122917-271110-150540-207703-168259-254128-286217-127585\" }"

    val gson = Gson()
    val t = gson.fromJson(jsonstring, StringMovie::class.java)
    println(t)
}