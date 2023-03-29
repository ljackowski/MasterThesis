<main>
    <div class="container">
        <div class="inner-container">
            <button on:click="{hideSQLTESTS}">SQL TESTS</button>
            <button on:click="{hideALGORITHMSSTESTS}">ALGORITHMS TESTS</button>
        </div>
        <div class="test-block">
            {#if showSQLTESTS}
                <div>
                    <div class="inner-container">
                        <textarea bind:value="{sqlRequest}" placeholder="Enter sql operation"></textarea>
                    </div>
                    <div class="inner-container">
                        <h3>SQL statement: </h3>
                        { sqlRequest }
                    </div>
                    <button on:click="{getDataFromDatabase}">Get</button>
                    <div class="inner-container">
                        <h3>Column Names</h3>
                        {#each sqlColumnNames as sqlColumnName}
                            <div class="inner-container">
                                { sqlColumnName }
                            </div>
                        {/each}
                    </div>
                </div>

                <div class="inner-container">
                    <textarea v-model="stringMovieToConvert" placeholder="String movie"></textarea>
                </div>
                <div class="inner-container">
                    <select v-model="selectedOperationType">
                        {#each sqlOperations as operation}
                            <option>
                                { operation }
                            </option>
                        {/each}
                    </select>
                </div>
                <button on:click="getDataFromDatabase">Operate on movie</button>
            {/if}

            {#if showALGORITHMSTESTS}
                <div>
                    <div>
                        <label for="pattern-to-match">Pattern to match</label>
                        <input bind:value="{patternToMatch}" id="pattern-to-match">
                        <label for="selected-algorithm-type">Search algorithm</label>
                        <input bind:value="{selectedAlgorithmType}" id="selected-algorithm-type">
                        <button on:click="{checkAlgorithm}">Check</button>
                    </div>
                    <h3>Algorithms</h3>
                    {#each algorithmTypes as algorithmType}
                        <div class="inner-container">
                            {algorithmType}
                        </div>
                    {/each}
                </div>
            {/if}

            {#if resultData != undefined}
                <div>
                    {#if resultData.testStartDate != undefined}
                        <div v-if="result.testStartDate != undefined">
                            <h4>Start of test: </h4>
                            <p>{resultData.testStartDate}</p>
                        </div>
                    {/if}
                    {#if resultData.testStopDate != undefined}
                        <div>
                            <h4>End of Test: </h4>
                            <p>{resultData.testStopDate}</p>
                        </div>
                    {/if}
                    {#if resultData.durationInMilli != undefined}
                        <div>
                            <h4>Duration: </h4>
                            <p>{resultData.durationInMilli}</p>
                        </div>
                    {/if}
                    {#if resultData.operationResult != undefined}
                        <div>
                            <h4>Affected rows: </h4>
                            <p>{resultData.operationResult}</p>
                        </div>
                    {/if}
                    {#if resultData.stringMovies != undefined}
                        <div>
                            <h4>Result from SQL: </h4>
                            <p>{ resultData.stringMovies }</p>
                        </div>
                    {/if}
                    {#if resultData.movies != undefined}
                        <div>
                            <h4>Result from SQL: </h4>
                            <p>{resultData.movies}</p>
                        </div>
                    {/if}
                    {#if resultData.resultList != undefined}
                        <div>
                            <h4>esult from text search: </h4>
                            <p>{resultData.resultList}</p>
                        </div>
                    {/if}
                </div>
            {/if}
        </div>
    </div>
</main>

<script>
    import axios from "axios";

    export let springAPIURL = "http://localhost:8080/spring-api/"
    export let algorithmTypes = ["Trie", "FineAutomata", "BadCharBoyer"]
    export let sqlColumnNames = ["movieId", "title", "original_language", "overview", "popularity", "release_date", "budget", "revenue",
        "runtime", "status", "tagline", "vote_average", "vote_count", "poster_path", "backdrop_path", "production_companies",
        "credits", "keywords", "genres", "recommendations", "id"]
    export let sqlRequest = ""
    export let patternToMatch = ""
    export let selectedAlgorithmType = ""
    export let resultData = undefined
    export let showSQLTESTS = true
    export let showALGORITHMSTESTS = false
    export let stringMovieToConvert: ""
    export let sqlOperations = ["DELETE", "INSERT INTO", "UPDATE"]
    export let selectedOperationType = "UPDATE"

    function hideSQLTESTS() {
        clearData()
        showALGORITHMSTESTS = false
        showSQLTESTS = !showSQLTESTS
    }

    function hideALGORITHMSSTESTS() {
        clearData()
        showSQLTESTS = false
        showALGORITHMSTESTS = !showALGORITHMSTESTS
    }

    function clearData() {
        selectedAlgorithmType = ""
        patternToMatch = ""
        resultData = undefined
        sqlRequest = ""
        selectedOperationType = ""
        stringMovieToConvert = ""
    }

    function checkAlgorithm() {
        axios.get(springAPIURL + "get/algorithm", {
            params: {
                patternToSearch: patternToMatch,
                algorithmType: selectedAlgorithmType
            }
        }).then(result => resultData = result.data)
    }

    function getDataFromDatabase() {
        if (sqlRequest.toLowerCase().includes("select")) {
            axios.get(springAPIURL + "get", {
                params: {
                    query: sqlRequest
                }
            }).then(result => resultData = result.data)
        } else {
            axios.post(springAPIURL + "operate", {}, {
                params: {
                    operationType: this.selectedOperationType,
                    stringMovies: this.stringMovieToConvert
                }
            }).then(result => resultData = result.data)
        }
    }
</script>

<style>
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