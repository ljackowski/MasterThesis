<main>
    <div class="container">
        <div class="inner-container">
            <button on:click="{hideSQLTESTS}">SQL TESTS</button>
            <button on:click="{hideALGORITHMSSTESTS}">ALGORITHMS TESTS</button>
        </div>
        <div>
            <label for="rails-api">Enable Rails Api: </label>
            <input type="checkbox" name="rails-api" id="rails-api" bind:value={isRails}>
        </div>
        <div class="test-block">
            <APITests bind:isRails={isRails} bind:sqlColumnNames={sqlColumnNames} bind:algorithmTypes={algorithmTypes}
                      bind:sqlOperations={sqlOperations} bind:showALGORITHMSTESTS={showALGORITHMSTESTS} bind:showSQLTESTS={showSQLTESTS}/>
            <ResultsTests bind:result={result}/>
        </div>
        <ImagesTests/>
    </div>

</main>

<script>
    import APITests from "../components/APITests.svelte";
    import ResultsTests from "../components/ResultsTests.svelte";
    import ImagesTests from "../components/ImagesTests.svelte";
    export let algorithmTypes = ["Trie", "FineAutomata", "BadCharBoyer"]
    export let sqlColumnNames = ["movieId", "title", "original_language", "overview", "popularity", "release_date", "budget", "revenue",
        "runtime", "status", "tagline", "vote_average", "vote_count", "poster_path", "backdrop_path", "production_companies",
        "credits", "keywords", "genres", "recommendations", "id"]
    export let sqlOperations = ["DELETE", "INSERT INTO", "UPDATE"]
    export let showSQLTESTS = true
    export let showALGORITHMSTESTS = false
    export let isRails = false
    export let result = undefined

    function hideSQLTESTS() {
        showALGORITHMSTESTS = false
        showSQLTESTS = !showSQLTESTS
    }

    function hideALGORITHMSSTESTS() {
        showSQLTESTS = false
        showALGORITHMSTESTS = !showALGORITHMSTESTS
    }

    function getResult(result) {
        result = result
    }
</script>

<style>
    .test-block {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        grid-template-rows: 1fr;
        grid-column-gap: 0;
        grid-row-gap: 0;
    }
</style>