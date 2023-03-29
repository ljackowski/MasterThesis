package net.jackowski.spring.util.service

import com.opencsv.CSVReaderBuilder
import net.jackowski.spring.model.Movie
import net.jackowski.spring.model.StringMovie
import net.jackowski.spring.util.algorithms.AlgorithmType
import net.jackowski.spring.util.algorithms.BadCharBoyer
import net.jackowski.spring.util.algorithms.FineAutomata
import net.jackowski.spring.util.algorithms.Trie
import net.jackowski.spring.util.repository.MovieRepository
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

@Service
class MovieService(private val movieRepository: MovieRepository, private val jdbcTemplate: JdbcTemplate) {

    private val logger = LoggerFactory.getLogger("Master Thesis")

    fun performAlgorithm(patternToSearch: String, algorithmType: String): List<String> {
        val bytes: ByteArray
        val patterForMatching: String
        when (algorithmType) {
            AlgorithmType.TRIE.type -> {
                val trie = Trie()
                val fileReaderScanner =
                    Scanner(FileReader(System.getProperty("user.dir") + "/Harry_Potter_Deathly_Hollows.txt")).useDelimiter(
                        "\\s"
                    )
                var word: String
                while (fileReaderScanner.hasNext()) {
                    word = fileReaderScanner.next()
                    if (word.contains(Regex("[^A-Za-z]"))) word = word.replace(Regex("[^A-Za-z]"), "")
                    if (word.isNotBlank()) trie.insert(word)
                }
                return arrayListOf(trie.search(patternToSearch).toString())
            }

            AlgorithmType.FINE_AUTOMATA.type -> {
                val fineAutomata = FineAutomata()
                bytes =
                    Files.readAllBytes(Path.of(System.getProperty("user.dir") + "/Harry_Potter_Deathly_Hollows.txt"))
                patterForMatching = String(bytes).replace(Regex("[^A-Za-z]"), "")
                return fineAutomata.search(patternToSearch, patterForMatching)
            }

            AlgorithmType.BAD_CHAR_BOYER.type -> {
                val badCharBoyer = BadCharBoyer()
                bytes =
                    Files.readAllBytes(Path.of(System.getProperty("user.dir") + "/Harry_Potter_Deathly_Hollows.txt"))
                patterForMatching = String(bytes).replace(Regex("[^A-Za-z]"), "")
                return badCharBoyer.search(patterForMatching, patternToSearch)
            }

            else -> {
                logger.warn("Algorithm not specified!")
                return arrayListOf("Algorithm not specified!")
            }
        }
    }

    fun getFromQuery(query: String): List<StringMovie> {
        return jdbcTemplate.query(query, BeanPropertyRowMapper(StringMovie::class.java))
    }

    fun operateOnMovie(operationType: String, stringMovies: List<StringMovie>): Boolean {
        val movies = movieRepository.findAll()
        try {
            return if (operationType.equals("DELETE", true)) {
                stringMovies.forEach { stringMovie ->
                    movies.removeIf { it.movieId == stringMovie.movieId }
                    movieRepository.delete(stringMovie)
                }
                true;
            } else if (operationType.equals("INSERT INTO", true)) {
                stringMovies.forEach { stringMovie ->
                    movies.add(stringMovie)
                    movieRepository.save(stringMovie)
                }
                true;
            } else if (operationType.equals("UPDATE", true)) {
                stringMovies.forEach { stringMovie ->
                    movies[movies.indexOf(movies.find { it.movieId == stringMovie.movieId })] = stringMovie
                    movieRepository.save(stringMovie)
                }
                true;
            } else {
                false;
            }
        } catch (e: Exception) {
            return false
        }
    }

    fun loadMoviesToDatabase() {
        val resourceFileCSV = System.getProperty("user.dir") + "/movies.csv"
        try {
            val reader = Files.newBufferedReader(Path.of(resourceFileCSV))
            val csvReader = CSVReaderBuilder(reader).withSkipLines(16632).build()
            var line: List<String>
            val stringMovie = StringMovie()
            while ((csvReader.readNext().also { line = it.toList() }) != null) {

                stringMovie.movieId = line[0]
                stringMovie.title = line[1]

                stringMovie.genres = line[2]

                stringMovie.originalLanguage = line[3]
                stringMovie.overview = line[4]
                stringMovie.popularity = line[5]

                stringMovie.productionCompanies = line[6]

                stringMovie.releaseDate = line[7]
                stringMovie.budget = line[8]
                stringMovie.revenue = line[9]
                stringMovie.runtime = line[10]
                stringMovie.status = line[11]
                stringMovie.tagline = line[12]
                stringMovie.voteAverage = line[13]
                stringMovie.voteCount = line[14]

                stringMovie.credits = line[15]

                stringMovie.keywords = line[16]

                stringMovie.posterPath = line[17]
                stringMovie.backdropPath = line[18]

                stringMovie.recommendations = line[19]

                movieRepository.save(stringMovie)

                stringMovie.clearObject()
                if (csvReader.linesRead.toInt() % 300 == 0) {
                    logger.info("Lines read: ${csvReader.linesRead}")
                    logger.info("Records read: ${csvReader.recordsRead}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun convertStringMovieToMovie(stringMovie: StringMovie): Movie {
        val movie = Movie()

        movie.movieId = stringMovie.movieId
        movie.title = stringMovie.title
        movie.originalLanguage = stringMovie.originalLanguage
        movie.overview = stringMovie.overview
        movie.posterPath = stringMovie.posterPath
        movie.backdropPath = stringMovie.backdropPath
        movie.status = stringMovie.status
        movie.tagline = stringMovie.tagline

        movie.releaseDate = convertDate(stringMovie.releaseDate)

        movie.popularity = stringMovie.popularity?.toDoubleOrNull()
        movie.budget = stringMovie.budget?.toDoubleOrNull()
        movie.revenue = stringMovie.revenue?.toDoubleOrNull()
        movie.runtime = stringMovie.runtime?.toDoubleOrNull()
        movie.voteAverage = stringMovie.voteAverage?.toDoubleOrNull()
        movie.voteCount = stringMovie.voteCount?.toDoubleOrNull()

        movie.genres = stringMovie.genres?.split("-")
        movie.productionCompanies = stringMovie.productionCompanies?.split("-")
        movie.credits = stringMovie.credits?.split("-")
        movie.keywords = stringMovie.keywords?.split("-")
        movie.recommendations = stringMovie.recommendations?.split("-")

        return movie
    }

    private fun convertDate(stringDate: String?): LocalDate? {
        val date: LocalDate?
        try {
            date = LocalDate.parse(stringDate, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: DateTimeParseException) {
            return null
        }
        return date
    }


}