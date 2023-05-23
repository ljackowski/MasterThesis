<template>
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
      <h4>String movies: </h4>
      <p>{{ result.stringMovies }}</p>
    </div>
    <div v-if="result.movies != undefined">
      <h4>Movies: </h4>
      <p>{{ result.movies }}</p>
    </div>
    <div v-if="result.resultList != undefined">
      <h4>Result from text search: </h4>
      <p>{{ result.resultList }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "Results-Tests",
  props: {
    result_: Object
  },
  data() {
    return {
      result: {
        testStartDate: "",
        testStopDate: "",
        durationInMilli : "",
        operationResult : "",
        stringMovies: [],
        movies: [],
        resultList: []
      },
    }
  }
}
</script>

<style scoped>

</style>