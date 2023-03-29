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
                    {#if result.movies != undefined}
                        <div>
                            <h4>Result from SQL: </h4>
                            <p>{result.movies}</p>
                        </div>
                    {/if}
                    {#if result.resultList != undefined}
                        <div>
                            <h4>esult from text search: </h4>
                            <p>{result.resultList}</p>
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
    export let sqlColumnNames = ["id", "title", "original_language", "overview", "popularity", "release_date", "budget", "revenue",
        "runtime", "status", "tagline", "vote_average", "vote_count", "poster_path", "backdrop_path", "production_companies",
        "credits", "keywords", "genres", "recommendations"]
    export let sqlRequest = ""
    export let patternToMatch = ""
    export let selectedAlgorithmType = ""
    export let result = undefined
    export let showSQLTESTS = true
    export let showALGORITHMSTESTS = false

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
        result = undefined
        sqlRequest = ""
    }

    function checkAlgorithm() {
        axios.get(springAPIURL + "get/algorithm", {
            params: {
                patternToSearch: patternToMatch,
                algorithmType: selectedAlgorithmType
            }
        }).then(result => result = result.data)
    }

    function getDataFromDatabase() {
        if (sqlRequest.toLowerCase().includes("select")) {
            axios.get(springAPIURL + "get", {
                params: {
                    query: sqlRequest
                }
            }).then(result => result = result)
        } else {
            axios.post(springAPIURL + "operate", {}, {
                params: {
                    query: sqlRequest
                }
            }).then(result => result = result)
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