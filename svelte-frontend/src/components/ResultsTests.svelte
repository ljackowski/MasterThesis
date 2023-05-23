<main>
    {#if result != undefined}
        <div>
            {#if result.testStartDate != undefined}
                <div v-if="result.testStartDate != undefined">
                    <h4>Start of test: </h4>
                    <p>{result.testStartDate}</p>
                </div>
            {/if}
            {#if result.testStopDate != undefined}
                <div>
                    <h4>End of Test: </h4>
                    <p>{result.testStopDate}</p>
                </div>
            {/if}
            {#if result.durationInMilli != undefined}
                <div>
                    <h4>Duration: </h4>
                    <p>{result.durationInMilli}</p>
                </div>
            {/if}
            {#if result.operationResult != undefined}
                <div>
                    <h4>Affected rows: </h4>
                    <p>{result.operationResult}</p>
                </div>
            {/if}
            {#if result.stringMovies != undefined}
                <div>
                    <h4>Result from SQL: </h4>
                    <p>{ result.stringMovies }</p>
                </div>
            {/if}
            {#if result.movies != undefined}
                <div>
                    <h4>Result from SQL: </h4>
                    <p>{result.movies}</p>
                </div>
            {/if}
            {#if result.resultList != undefined}
                <div>
                    <h4>Result from text search: </h4>
                    <p>{result.resultList}</p>
                </div>
            {/if}
        </div>
    {/if}
</main>

<script>
   export let result = {
       testStartDate: "",
       testStopDate: "",
       durationInMilli: "",
       operationResult: "",
       stringMovies: [],
       movies: [],
       resultList: []
   }
</script>