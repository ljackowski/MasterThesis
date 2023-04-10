class StringMovie < ApplicationRecord
  self.table_name = "movies"

  def convert_string_to_typed
    @movie = Movie.new
    @movie.movie_id = self.movie_id
    @movie.title = self.title
    @movie.original_language = self.original_language
    @movie.overview = self.overview
    @movie.popularity = self.popularity.to_f rescue nil
    @movie.release_date = DateTime.parse(self.release_date) rescue nil
    @movie.budget = self.budget.to_f rescue nil
    @movie.revenue = self.revenue.to_f rescue nil
    @movie.runtime = self.runtime.to_f rescue nil
    @movie.status = self.status
    @movie.tagline = self.tagline
    @movie.vote_average = self.vote_average.to_f rescue nil
    @movie.vote_count = self.vote_average.to_f rescue nil
    @movie.poster_path = self.poster_path
    @movie.backdrop_path = self.backdrop_path
    @movie.production_companies = self.production_companies.split("-") rescue nil
    @movie.credits = self.credits.split("-") rescue nil
    @movie.keywords = self.keywords.split("-") rescue nil
    @movie.genres = self.genres.split("-") rescue nil
    @movie.recommendations = self.recommendations.split("-") rescue nil
    @movie
  end
end
