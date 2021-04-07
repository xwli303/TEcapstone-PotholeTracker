<template>
    <div class="side-container">
        <div class="topline">HOME</div>
        <div class="add-pothole">
            <form id="report-pothole">
                <input type="text">
            </form>
        </div>
    </div>
</template>

<script>
export default {
    name: "SideMenu"
}
</script>

<style>
.side-container {
    display: grid;
    grid-template-areas: 
        "topline"
        "add-pothole"
        "add-pothole"
        "add-pothole";
}
.add-pothole {
    grid-area: add-pothole;
}
.topline {
    grid-area: topline;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>