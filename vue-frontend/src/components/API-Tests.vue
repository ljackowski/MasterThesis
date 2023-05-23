<template>
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
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "API-Tests",
  props: {
    isRails: Boolean,
    showSQLTESTS: Boolean,
    showALGORITHMSTESTS: Boolean,
    sqlColumnNames: Array,
    algorithmTypes: Array,
    sqlOperations: Array,
    getResult: Function
  },
  data() {
    return {
      springAPIURL: "http://localhost:8080/spring-api/",
      railsAPRIURL: "http://localhost:3000/rails-api/",
      selectedOperationType: "UPDATE",
      sqlRequest: "",
      patternToMatch: "",
      selectedAlgorithmType: "",
      result: undefined,
      stringMovieToConvert: ""
    }
  },
  methods: {
    checkAlgorithm() {
      if (this.isRails) {
        axios.get(this.railsAPRIURL + "get/algorithm", {
          params: {
            patternToSearch: this.patternToMatch,
            algorithmType: this.selectedAlgorithmType
          }
        }).then(result => this.getResult(result))
      } else {
        axios.get(this.springAPIURL + "get/algorithm", {
          params: {
            patternToSearch: this.patternToMatch,
            algorithmType: this.selectedAlgorithmType
          }
        }).then(result => this.getResult(result))
      }
    },
    getDataFromDatabase() {
      if (this.isRails) {
        if (this.sqlRequest.toLowerCase().includes("select")) {
          axios.get(this.railsAPRIURL + "get", {
            params: {
              query: this.sqlRequest
            }
          }).then(result => this.getResult(result))
        } else {
          axios.post(this.railsAPRIURL + "operate", {}, {
            params: {
              operationType: this.selectedOperationType,
              stringMovies: this.stringMovieToConvert
            }
          }).then(result => this.getResult(result))
        }
      } else {
        if (this.sqlRequest.toLowerCase().includes("select")) {
          axios.get(this.springAPIURL + "get", {
            params: {
              query: this.sqlRequest
            }
          }).then(result => this.getResult(result))
        } else {
          axios.post(this.springAPIURL + "operate", {}, {
            params: {
              operationType: this.selectedOperationType,
              stringMovies: this.stringMovieToConvert
            }
          }).then(result => this.getResult(result))
        }
      }
    }
  }
}
</script>

<style scoped>

</style>