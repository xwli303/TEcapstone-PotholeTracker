<template>
    <div>
        <!-- <div id="map" ref="map"></div> -->
        <GmapMap
            :center="{lat: 39.9528, lng: -75.1635}"
            :zoom="13"
            map-type-id="terrain"
            style="width: 800px; height: 800px"
        >
        <!-- <GMapMarker 
        v-bind:key="index" 
        v-for="(pothole, index) in $store.state.potholes"
        v-bind:position="pothole.location"
        /> -->
        </GmapMap>
    </div>
</template>

<script>
export default {
    name: "PotholeMap",
    // data() {
    //     return {
    //         map: null
    //     }
    // },
    // mounted() {
    //     this.map = new window.google.maps.Map(this.$refs["map"], {
    //         center: { lat: 39.9528, lng: -75.1635 },
    //         zoom: 10
    //     })
    // }
}
</script>

<style>
/* #map {
    height: 600px;
} */
</style>