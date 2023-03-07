package net.jackowski.spring.util.service

import com.opencsv.CSVReaderBuilder
import net.jackowski.spring.model.Movie
import net.jackowski.spring.util.repository.MovieRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Service
class MovieService(private val movieRepository: MovieRepository) {

    private val logger = LoggerFactory.getLogger("Master Thesis")

    fun loadMoviesToDatabase() {
        val resourceFileCSV = System.getProperty("user.dir") + "/spring/movies.csv"
        try {
            val reader = Files.newBufferedReader(Path.of(resourceFileCSV))
            val csvReader = CSVReaderBuilder(reader).withSkipLines(1).build()
            var line: List<String>
            val movie = Movie()
            var iterator = 16000
            while ((csvReader.readNext().also { line = it.toList() }) != null) {
                movie.movieId = line[0]
                movie.title = line[1]

                movie.genres?.name = line[2]
                movie.genres?.movieGenre = movie

                movie.originalLanguage = line[3]
                movie.overview = line[4]
                movie.popularity = line[5].toDoubleOrNull()

                movie.productionCompanies?.name = line[6]
                movie.productionCompanies?.movieProductionCompany = movie

                movie.releaseDate = convertDate(line[7])
                movie.budget = line[8].toDoubleOrNull()
                movie.revenue = line[9].toDoubleOrNull()
                movie.runtime = line[10].toDoubleOrNull()
                movie.status = line[11]
                movie.tagline = line[12]
                movie.voteAverage = line[13].toDoubleOrNull()
                movie.voteCount = line[14].toDoubleOrNull()

                movie.credits?.name = line[15]
                movie.credits?.movieCredit = movie

                movie.keywords?.name = line[16]
                movie.keywords?.movieKeyword = movie

                movie.posterPath = line[17]
                movie.backdropPath = line[18]

                movie.recommendations?.name = line[19]
                movie.recommendations?.movieRecommendation = movie

                movieRepository.save(movie)

                movie.clearObject()
                iterator++
                if (iterator >= 100000) {
                    break
                } else if(iterator % 300 == 0){
                    logger.info(iterator.toString())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun convertDate(stringDate: String): LocalDate?{
        var date: LocalDate? = null
        try {
            date = LocalDate.parse(stringDate, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: DateTimeParseException){
            return date
        }
        return date
    }


}