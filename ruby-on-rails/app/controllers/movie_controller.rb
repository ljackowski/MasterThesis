class MovieController < ApplicationController
  def get_test
    start = DateTime.now
    string_movies = StringMovie.limit(5)
    movies_array = []
    string_movies.each { |string_movie|
      movies_array.push(string_movie.convert_string_to_typed)
    }
    stop = DateTime.now

    test_result = TestResult.new
    test_result.testStartDate = start.to_s
    test_result.testStopDate = stop.to_s
    test_result.durationInMilli = (stop.to_time - start.to_time).in_milliseconds
    test_result.movies = movies_array
    test_result.stringMovies = string_movies

    render json: test_result, status: :ok
  end

  def operate_on_movie
    start = DateTime.now
    result = operate(params[:operationType], params[:stringMovies])
    stop = DateTime.now
    test_result = TestResult.new
    test_result.testStartDate = start.to_s
    test_result.testStopDate = stop.to_s
    test_result.durationInMilli = (stop.to_time - start.to_time).in_milliseconds
    test_result.operationResult = result
    render json: test_result, status: :ok
  end

  def check_algorithm
    start = DateTime.now
    result = operate(params[:operationType], params[:stringMovies])
    stop = DateTime.now
    test_result = TestResult.new
    test_result.testStartDate = start.to_s
    test_result.testStopDate = stop.to_s
    test_result.durationInMilli = (stop.to_time - start.to_time).in_milliseconds
    test_result.resultList = result
    render json: test_result, status: :ok
  end

  private

  require 'json'

  def operate(operation_type, string_movies)
    json_string_movies = JSON.parse(string_movies)
    movies_table = StringMovie.all
    movies_array = movies_table.to_a
    movies_for_operate = []
    json_string_movies.each do |json_string_movie|
      movie = StringMovie.new
      movie.id = json_string_movie["id"]
      movie.movie_id = json_string_movie["movieId"]
      movie.title = json_string_movie["title"]
      movie.original_language = json_string_movie["originalLanguage"]
      movie.overview = json_string_movie["overview"]
      movie.popularity = json_string_movie["popularity"]
      movie.release_date = json_string_movie["releaseDate"]
      movie.budget = json_string_movie["budget"]
      movie.revenue = json_string_movie["revenue"]
      movie.runtime = json_string_movie["runtime"]
      movie.status = json_string_movie["status"]
      movie.tagline = json_string_movie["tagline"]
      movie.vote_average = json_string_movie["voteAverage"]
      movie.vote_count = json_string_movie["voteCount"]
      movie.poster_path = json_string_movie["posterPath"]
      movie.backdrop_path = json_string_movie["backdropPath"]
      movie.production_companies = json_string_movie["productionCompanies"]
      movie.credits = json_string_movie["credits"]
      movie.keywords = json_string_movie["keywords"]
      movie.genres = json_string_movie["genres"]
      movie.recommendations = json_string_movie["recommendations"]
      movies_for_operate.push(movie)
    end

    if "DELETE".casecmp(operation_type) == 0
      movies_for_operate.each do |string_movie_for_operate|
        movies_array.delete_if { |string_movie| string_movie_for_operate.movie_id == string_movie.movie_id }
        string_movie_to_destroy = StringMovie.where(movie_id: string_movie_for_operate.movie_id).first
        if string_movie_to_destroy != nil
          string_movie_to_destroy.destroy
        end
      end
      return true
    elsif "UPDATE".casecmp(operation_type) == 0
      movies_for_operate.each do |string_movie_for_operate|
        movies_array = movies_array.map { |string_movie_| string_movie_.movie_id == string_movie_for_operate.movie_id ? string_movie_for_operate : string_movie_ }
        StringMovie.update(string_movie_for_operate.id,
                           :movie_id => string_movie_for_operate.movie_id,
                           :title => string_movie_for_operate.title,
                           :original_language => string_movie_for_operate.original_language,
                           :overview => string_movie_for_operate.overview,
                           :popularity => string_movie_for_operate.popularity,
                           :release_date => string_movie_for_operate.release_date,
                           :budget => string_movie_for_operate.budget,
                           :revenue => string_movie_for_operate.revenue,
                           :runtime => string_movie_for_operate.runtime,
                           :status => string_movie_for_operate.status,
                           :tagline => string_movie_for_operate.tagline,
                           :vote_average => string_movie_for_operate.vote_average,
                           :vote_count => string_movie_for_operate.vote_count,
                           :poster_path => string_movie_for_operate.poster_path,
                           :backdrop_path => string_movie_for_operate.backdrop_path,
                           :production_companies => string_movie_for_operate.production_companies,
                           :credits => string_movie_for_operate.credits,
                           :keywords => string_movie_for_operate.keywords,
                           :genres => string_movie_for_operate.genres,
                           :recommendations => string_movie_for_operate.recommendations
        )
      end
      return true
    elsif "INSERT INTO".casecmp(operation_type) == 0
      movies_for_operate.each do |string_movie_for_operate|
        movies_array.push(string_movie_for_operate)
        StringMovie.new(:id => string_movie_for_operate.id,
                        :movie_id => string_movie_for_operate.movie_id,
                        :title => string_movie_for_operate.title,
                        :original_language => string_movie_for_operate.original_language,
                        :overview => string_movie_for_operate.overview,
                        :popularity => string_movie_for_operate.popularity,
                        :release_date => string_movie_for_operate.release_date,
                        :budget => string_movie_for_operate.budget,
                        :revenue => string_movie_for_operate.revenue,
                        :runtime => string_movie_for_operate.runtime,
                        :status => string_movie_for_operate.status,
                        :tagline => string_movie_for_operate.tagline,
                        :vote_average => string_movie_for_operate.vote_average,
                        :vote_count => string_movie_for_operate.vote_count,
                        :poster_path => string_movie_for_operate.poster_path,
                        :backdrop_path => string_movie_for_operate.backdrop_path,
                        :production_companies => string_movie_for_operate.production_companies,
                        :credits => string_movie_for_operate.credits,
                        :keywords => string_movie_for_operate.keywords,
                        :genres => string_movie_for_operate.genres,
                        :recommendations => string_movie_for_operate.recommendations).save
      end
      return true
    end
    false
  end

  def perform_algorithm

  end
end
