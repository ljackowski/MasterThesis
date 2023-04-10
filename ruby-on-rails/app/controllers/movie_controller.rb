class MovieController < ApplicationController
  def get_test
    @start = DateTime.now
    @string_movies = StringMovie.limit(5)
    @movies_array = []
    @string_movies.each { |string_movie|
      @movies_array.push(string_movie.convert_string_to_typed)
    }
    @stop = DateTime.now

    @test_result = TestResult.new
    @test_result.testStartDate = @start.to_s
    @test_result.testStopDate = @stop.to_s
    @test_result.durationInMilli = (@stop.to_time - @start.to_time).in_milliseconds
    @test_result.movies = @movies_array
    @test_result.stringMovies = @string_movies

    render json: @test_result, status: :ok
  end

  def operate_on_movie
    @test2 = "[{ \"movieId\": \"135397\", \"title\": \"Jurassic World\", \"originalLanguage\": \"en\", \"overview\": \"Twenty-two years after the events of Jurassic Park Isla Nublar now features a fully functioning dinosaur theme park Jurassic World as originally envisioned by John Hammond.\", \"popularity\": \"99.327\", \"releaseDate\": \"2015-06-12\", \"budget\": \"150000000.0\", \"revenue\": \"1671537444.0\", \"runtime\": \"124.0\", \"status\": \"Released\", \"tagline\": \"The park is open.\", \"voteAverage\": \"6.678\", \"voteCount\": \"18835.0\", \"posterPath\": \"/A0LZHXUzo5C60Oahvt7VxvwuzHw.jpg\", \"backdropPath\": \"/aIGIYJTyOkEVUmEd3z5x6diYsFx.jpg\", \"productionCompanies\": \"Universal Pictures-Amblin Entertainment\", \"credits\": \"Chris Pratt-Bryce Dallas Howard-Ty Simpkins-Nick Robinson-Vincent D'Onofrio-BD Wong-Omar Sy-Jake Johnson-Irrfan Khan-Judy Greer-Lauren Lapkus-Brian Tee-Katie McGrath-Andy Buckley-Eric Edelstein-Courtney J. Clark-Colby Boothman-Jimmy Fallon-James DuMont-Matt Burke-Anna Talakkottur-Matthew Cardarople-Michael Papajohn-William Gary Smith-Kelly Washington-Isaac Keys-Patrick Crowley-Chad Randall-Gary Weeks-Bill Ogilvie-Allan Tam-Yvonne Angulo-Chloe Perrin-Timothy Eulich-Kevin Foster-Bonnie Wild-Brad Bird-Colin Trevorrow-Justin Lacalamita-Tiffany Forest-Arlene Newman-Tait Fletcher-Jimmy Buffett-Tim Connolly-Johnny Otto-Erika Erica-Brandon Marc Higa-Martin Klebba-Eddie J. Fernandez\", \"keywords\": \"dna-tyrannosaurus rex-velociraptor-island-animal attack-primal fear-sequel-disaster-escape-dinosaur-creature-park-amusement park-theme park-genetic engineering-raptor-animal horror\", \"genres\": \"Action-Adventure-Science Fiction-Thriller\", \"recommendations\": \"351286-331-329-99861-330-76341-102899-118340-87101-140607-131631-198663-293660-122917-271110-150540-207703-168259-254128-286217-127585\" }]"
    @test_result = operate("test", @test2)
    render json: @test_result, status: :ok
  end


  private
  require 'json'
  def operate(operation_type, string_movies)
    json_string_movies = JSON.parse(string_movies)
    movie_array = []
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
      movie_array.push(movie)
    end

    movie_array.each do |movie|

    end


    movie_array
  end

end
