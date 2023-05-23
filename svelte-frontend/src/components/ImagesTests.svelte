<main>
    <div class="test-block">
        <div>
            <img src="att1.jpg" alt="att1">
            <img src="att2.jpg" alt="att2">
            <img src="att3.jpg" alt="att3">
            <img src="att4.jpg" alt="att4">
        </div>
        <div>
            <img src="att1.jpg" alt="att1">
            <img src="att2.jpg" alt="att2">
            <img src="att3.jpg" alt="att3">
            <img src="att4.jpg" alt="att4">
        </div>
    </div>
    <div class="test-block">
        <div>
            <img src="att1.jpg" alt="att1">
            <img src="att2.jpg" alt="att2">
            <img src="att3.jpg" alt="att3">
            <img src="att4.jpg" alt="att4">
        </div>
        <div>
            <img src="att1.jpg" alt="att1">
            <img src="att2.jpg" alt="att2">
            <img src="att3.jpg" alt="att3">
            <img src="att4.jpg" alt="att4">
        </div>
    </div>
</main>

<style>
    .test-block {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        grid-template-rows: 1fr;
        grid-column-gap: 0;
        grid-row-gap: 0;
    }
    img {
        width: 90%;
        padding: 5%;
    }
</style>