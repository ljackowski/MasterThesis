Rails.application.routes.draw do
  get '/rails-api/get', to: 'movie#get_test'
  get '/rails-api/get1', to: 'movie#operate_on_movie'
end
