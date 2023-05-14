class StringMovie < ApplicationRecord
  self.table_name = "movies"
  self.primary_key = :id
end
