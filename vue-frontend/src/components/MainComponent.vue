<template>
  <div class="container">
    <div class="inner-container">
      <button @click="hideSQLTESTS">SQL TESTS</button>
      <button @click="hideALGORITHMSSTESTS">ALGORITHMS TESTS</button>
    </div>
    <div>
      <label for="rails-api">Enable Rails Api: </label>
      <input type="checkbox" name="rails-api" id="rails-api" v-model="isRails">
    </div>
    <div class="test-block">
      <APITests :is-rails="isRails" :sqlColumnNames="sqlColumnNames" :algorithm-types="algorithmTypes"
                :sql-operations="sqlOperations" :get-result="getResult" :show-a-l-g-o-r-i-t-h-m-s-t-e-s-t-s="showALGORITHMSTESTS" :show-s-q-l-t-e-s-t-s="showSQLTESTS"/>
      <ResultsTests :result="result"/>
    </div>

    <ImagesTests/>
  </div>
</template>

<script>
import APITests from "@/components/API-Tests.vue";
import ResultsTests from "@/components/Results-Tests.vue";
import ImagesTests from "@/components/Images-Tests.vue";
export default {
  components: {
    APITests,
    ResultsTests,
    ImagesTests
  },
  data() {
    return {
      isRails: false,
      algorithmTypes: ["Trie", "FineAutomata", "BadCharBoyer"],
      sqlColumnNames: ["movieId", "title", "original_language", "overview", "popularity", "release_date", "budget", "revenue",
        "runtime", "status", "tagline", "vote_average", "vote_count", "poster_path", "backdrop_path", "production_companies",
        "credits", "keywords", "genres", "recommendations", "id"],
      sqlOperations: ["DELETE", "INSERT INTO", "UPDATE"],
      result: undefined,
      showSQLTESTS: true,
      showALGORITHMSTESTS: false,
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
    getResult(result) {
      this.result = result
    }
  }
}
</script>

<style>
body{
  background: rgb(255,255,255);
  background: linear-gradient(90deg, rgba(255,255,255,1) 0%, rgba(206,30,240,1) 34%, rgba(29,79,203,1) 84%);
}
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
