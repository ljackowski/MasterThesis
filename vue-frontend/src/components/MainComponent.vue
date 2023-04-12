<template>
  <div class="container">
    <div class="inner-container">
      <button @click="hideSQLTESTS">SQL TESTS</button>
      <button @click="hideALGORITHMSSTESTS">ALGORITHMS TESTS</button>
    </div>
    <div>
      <label for="rails-api">Enable Rails Api: </label>
      <input type="radio" name="rails-api" id="rails-api" v-model="isRails">
    </div>
    <div class="test-block">
      <div v-if="showSQLTESTS">
        <div class="inner-container">
          <textarea v-model="sqlRequest" placeholder="Enter sql operation"></textarea>
        </div>
        <div class="inner-container">
          <h3>SQL statement: </h3>
          {{ sqlRequest }}
        </div>
        <button @click="getDataFromDatabase">Get</button>
        <div class="inner-container">
          <h3>Column Names</h3>
          <div v-for="sqlColumnName in sqlColumnNames" :key="sqlColumnName" class="inner-container">
            {{ sqlColumnName }}
          </div>
        </div>

        <div class="inner-container">
          <textarea v-model="stringMovieToConvert" placeholder="String movie"></textarea>
        </div>
        <div class="inner-container">
          <select v-model="selectedOperationType">
            <option v-for="operation in sqlOperations" :value="operation" :key="operation">
              {{ operation }}
            </option>
          </select>
        </div>
        <button @click="getDataFromDatabase">Operate on movie</button>
      </div>

      <div v-if="showALGORITHMSTESTS">
        <div>
          <label for="pattern-to-match">Pattern to match</label>
          <input v-model="patternToMatch" id="pattern-to-match">
          <label for="selected-algorithm-type">Search algorithm</label>
          <input v-model="selectedAlgorithmType" id="selected-algorithm-type">
          <button @click="checkAlgorithm">Check</button>
        </div>
        <h3>Algorithms</h3>
        <div v-for="algorithmType in algorithmTypes" :key="algorithmType" class="inner-container">
          {{ algorithmType }}
        </div>
      </div>

      <div v-if="result != undefined">
        <div v-if="result.testStartDate != undefined">
          <h4>Start of test: </h4>
          <p>{{ result.testStartDate }}</p>
        </div>
        <div v-if="result.testStopDate != undefined">
          <h4>End of Test: </h4>
          <p>{{ result.testStopDate }}</p>
        </div>
        <div v-if="result.durationInMilli != undefined">
          <h4>Duration: </h4>
          <p>{{ result.durationInMilli }}</p>
        </div>
        <div v-if="result.operationResult != undefined">
          <h4>Affected rows: </h4>
          <p>{{ result.operationResult }}</p>
        </div>
        <div v-if="result.stringMovies != undefined">
          <h4>Result from SQL: </h4>
          <p>{{ result.stringMovies }}</p>
        </div>
        <div v-if="result.movies != undefined">
          <h4>Result from SQL: </h4>
          <p>{{ result.movies }}</p>
        </div>
        <div v-if="result.resultList != undefined">
          <h4>Result from text search: </h4>
          <p>{{ result.resultList }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      springAPIURL: "http://localhost:8080/spring-api/",
      railsAPRIURL: "http://localhost:3000/rails-api/",
      isRails: false,
      algorithmTypes: ["Trie", "FineAutomata", "BadCharBoyer"],
      sqlColumnNames: ["movieId", "title", "original_language", "overview", "popularity", "release_date", "budget", "revenue",
        "runtime", "status", "tagline", "vote_average", "vote_count", "poster_path", "backdrop_path", "production_companies",
        "credits", "keywords", "genres", "recommendations", "id"],
      sqlRequest: "",
      patternToMatch: "",
      selectedAlgorithmType: "",
      result: undefined,
      showSQLTESTS: true,
      showALGORITHMSTESTS: false,
      stringMovieToConvert: "",
      sqlOperations: ["DELETE", "INSERT INTO", "UPDATE"],
      selectedOperationType: "UPDATE"
    }
  },
  methods: {
    hideSQLTESTS() {
      this.clearData()
      this.showALGORITHMSTESTS = false
      this.showSQLTESTS = !this.showSQLTESTS
    },
    hideALGORITHMSSTESTS() {
      this.clearData()
      this.showSQLTESTS = false
      this.showALGORITHMSTESTS = !this.showALGORITHMSTESTS
    },
    clearData() {
      this.selectedAlgorithmType = ""
      this.patternToMatch = ""
      this.result = undefined
      this.sqlRequest = ""
      this.selectedOperationType = ""
      this.stringMovieToConvert = ""
    },
    checkAlgorithm() {
      if (this.isRails) {
        axios.get(this.railsAPRIURL + "get/algorithm", {
          params: {
            patternToSearch: this.patternToMatch,
            algorithmType: this.selectedAlgorithmType
          }
        }).then(result => this.result = result.data)
      } else {
        axios.get(this.springAPIURL + "get/algorithm", {
          params: {
            patternToSearch: this.patternToMatch,
            algorithmType: this.selectedAlgorithmType
          }
        }).then(result => this.result = result.data)
      }
    },
    getDataFromDatabase() {
      if (this.isRails) {
        if (this.sqlRequest.toLowerCase().includes("select")) {
          axios.get(this.railsAPRIURL + "get", {
            params: {
              query: this.sqlRequest
            }
          }).then(result => this.result = result.data)
        } else {
          axios.post(this.railsAPRIURL + "operate", {}, {
            params: {
              operationType: this.selectedOperationType,
              stringMovies: this.stringMovieToConvert
            }
          }).then(result => this.result = result.data)
        }
      } else {
        if (this.sqlRequest.toLowerCase().includes("select")) {
          axios.get(this.springAPIURL + "get", {
            params: {
              query: this.sqlRequest
            }
          }).then(result => this.result = result.data)
        } else {
          axios.post(this.springAPIURL + "operate", {}, {
            params: {
              operationType: this.selectedOperationType,
              stringMovies: this.stringMovieToConvert
            }
          }).then(result => this.result = result.data)
        }
      }
    }
  }
}
</script>

<style scoped>
.container {
  padding-left: 10px;
}

.inner-container {
  padding: 5px;
}

label {
  margin: 0 8px;
}

input {
  margin: 0 5px;
}

textarea {
  width: 600px;
  height: 150px;
  font-size: 16px;
}

.test-block {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: 1fr;
  grid-column-gap: 0;
  grid-row-gap: 0;
}
</style>
