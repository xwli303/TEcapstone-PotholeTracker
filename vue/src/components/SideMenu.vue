<template>
    <div class="side-container">
        <div class="topline">HOME</div>
        <div class="add-pothole">
            <new-pothole-form/>
        </div>
    </div>
</template>

<script>
import NewPotholeForm from './NewPotholeForm.vue'
export default {
    name: "SideMenu",
    components: {
        NewPotholeForm
    }
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