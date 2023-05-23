<main>
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
                <button on:click={getDataFromDatabase}>Get</button>
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
                <textarea bind:value={stringMovieToConvert} placeholder="String movie"></textarea>
            </div>
            <div class="inner-container">
                <select bind:value={selectedOperationType}>
                    {#each sqlOperations as operation}
                        <option>
                            { operation }
                        </option>
                    {/each}
                </select>
            </div>
            <button on:click={getDataFromDatabase}>Operate on movie</button>
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
    </div>
</main>

<script>
    import axios from 'axios'

    export let springAPIURL = "http://localhost:8080/spring-api/"
    export let railsAPRIURL = "http://localhost:3000/rails-api/"
    export let sqlRequest = ""
    export let patternToMatch = ""
    export let selectedAlgorithmType = ""
    export let resultData = undefined
    export let stringMovieToConvert = ""
    export let selectedOperationType = "UPDATE"
    export let isRails
    export let showSQLTESTS
    export let showALGORITHMSTESTS
    export let sqlColumnNames
    export let algorithmTypes
    export let sqlOperations

    function checkAlgorithm() {
        if (isRails) {
            axios.get(railsAPRIURL + "get/algorithm", {
                params: {
                    patternToSearch: patternToMatch,
                    algorithmType: selectedAlgorithmType
                }
            }).then(result => resultData = result)
        } else {
            axios.get(springAPIURL + "get/algorithm", {
                params: {
                    patternToSearch: patternToMatch,
                    algorithmType: selectedAlgorithmType
                }
            }).then(result => resultData = result)
        }
    }

    function getDataFromDatabase() {
        if (isRails) {
            if (sqlRequest.toLowerCase().includes("select")) {
                axios.get(railsAPRIURL + "get", {
                    params: {
                        query: sqlRequest
                    }
                }).then(result => resultData = result)
            } else {
                axios.post(railsAPRIURL + "operate", {}, {
                    params: {
                        operationType: selectedOperationType,
                        stringMovies: stringMovieToConvert
                    }
                }).then(result => resultData = result)
            }
        } else {
            if (sqlRequest.toLowerCase().includes("select")) {
                axios.get(springAPIURL + "get", {
                    params: {
                        query: sqlRequest
                    }
                }).then(result => resultData = result)
            } else {
                axios.post(springAPIURL + "operate", {}, {
                    params: {
                        operationType: selectedOperationType,
                        stringMovies: stringMovieToConvert
                    }
                }).then(result => resultData = result)
            }
        }
    }
</script>