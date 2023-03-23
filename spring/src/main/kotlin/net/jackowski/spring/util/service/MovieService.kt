package net.jackowski.spring.util.service

import com.opencsv.CSVReaderBuilder
import net.jackowski.spring.model.Movie
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
import java.util.*
import kotlin.collections.ArrayList

@Service
class MovieService(private val movieRepository: MovieRepository, private val jdbcTemplate: JdbcTemplate) {

    private val logger = LoggerFactory.getLogger("Master Thesis")

    fun performAlgorithm(patternToSearch: String, algorithmType: String): List<String> {
        val bytes: ByteArray
        val patterForMatching: String
        when (algorithmType) {
            AlgorithmType.TRIE.type -> {
                val trie = Trie()
                val fileReaderScanner = Scanner(FileReader(System.getProperty("user.dir") + "/Harry_Potter_Deathly_Hollows.txt")).useDelimiter("\\s")
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
                bytes = Files.readAllBytes(Path.of(System.getProperty("user.dir") + "/Harry_Potter_Deathly_Hollows.txt"))
                patterForMatching = String(bytes).replace(Regex("[^A-Za-z]"),"")
                return fineAutomata.search(patternToSearch, patterForMatching)
            }
            AlgorithmType.BAD_CHAR_BOYER.type -> {
                val badCharBoyer = BadCharBoyer()
                bytes = Files.readAllBytes(Path.of(System.getProperty("user.dir") + "/Harry_Potter_Deathly_Hollows.txt"))
                patterForMatching = String(bytes).replace(Regex("[^A-Za-z]"),"")
                return badCharBoyer.search(patterForMatching, patternToSearch)
            }
            else -> {
                logger.warn("Algorithm not specified!")
                return arrayListOf("Algorithm not specified!")
            }
        }
    }

    fun getFromQuery(query: String): List<Movie> {
        return jdbcTemplate.query(query, BeanPropertyRowMapper(Movie::class.java))
    }

    fun operateOnMovie(query: String): Int {
        return jdbcTemplate.update(query)
    }

    fun loadMoviesToDatabase() {
        val resourceFileCSV = System.getProperty("user.dir") + "/movies.csv"
        try {
            val reader = Files.newBufferedReader(Path.of(resourceFileCSV))
            val csvReader = CSVReaderBuilder(reader).withSkipLines(16632).build()
            var line: List<String>
            val movie = Movie()
            while ((csvReader.readNext().also { line = it.toList() }) != null) {

                movie.movieId = line[0]
                movie.title = line[1]

                movie.genres = line[2]

                movie.originalLanguage = line[3]
                movie.overview = line[4]
                movie.popularity = line[5]

                movie.productionCompanies = line[6]

                movie.releaseDate = line[7]
                movie.budget = line[8]
                movie.revenue = line[9]
                movie.runtime = line[10]
                movie.status = line[11]
                movie.tagline = line[12]
                movie.voteAverage = line[13]
                movie.voteCount = line[14]

                movie.credits = line[15]

                movie.keywords = line[16]

                movie.posterPath = line[17]
                movie.backdropPath = line[18]

                movie.recommendations = line[19]

                movieRepository.save(movie)

                movie.clearObject()
                if (csvReader.linesRead.toInt() % 300 == 0) {
                    logger.info("Lines read: ${csvReader.linesRead}")
                    logger.info("Records read: ${csvReader.recordsRead}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}