Rails.application.routes.draw do
  get '/rails-api/get', to: 'movie#get_test'
  get '/rails-api/operate', to: 'movie#operate_on_movie'
  get '/rails-api/get/algorithm', to: 'movie#check_algorithm'
end
