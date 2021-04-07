<template>
    <div>
        <div id="map" ref="map"></div>
        <!-- <GmapMap
            v-bind:center="{lat: 39.9528, lng: -75.1635}"
            v-bind:zoom="15"
            map-type-id="terrain"
            style="width: 800px; height: 800px"
        >
            <gmap-marker 
                v-for="(pothole, index) in $store.state.potholes"
                v-bind:key="index" 
                v-bind:position="pothole.location"
                v-bind:clickable="true"
                v-bind:draggable="true"
            />
        </GmapMap> -->
    </div>
</template>

<script>
import { Loader } from "@googlemaps/js-api-loader";
export default {
    data() {
        return {
            map: null
        }
    },
    name: "PotholeMap",
    mounted() {
        const loader = new Loader({
            apiKey: "AIzaSyDRcIpT0bxsU80JISszJUMH2uyxYpZ2I-w",
            version: "weekly"
        });
        loader.load().then(() => {
            this.map = new window.google.maps.Map(document.getElementById("map"), {
                center: { lat: 39.9528, lng: -75.1635 },
                zoom: 15
            });
            console.log(this.map);
            for (let i = 0; i < this.$store.state.potholes.length; i++) {
                const marker = new window.google.maps.Marker({
                    position: this.$store.state.potholes[i].location,
                });
                marker.setMap(this.map);
            }
        })
    }
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
#map {
    height: 600px;
}
</style>