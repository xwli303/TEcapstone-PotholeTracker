<template>
    <div>
        <div v-show="!isLoading" id="map" ref="map"></div>
    </div>
</template>

<script>
import { Loader } from "@googlemaps/js-api-loader";
export default {
    data() {
        return {
            map: null,
            // isLoading: true
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
                zoom: 12
            });
            let lat;
            let lng;
            for (let i = 0; i < this.$store.state.potholes.length; i++) {
                lat = this.$store.state.potholes[i].latitude;
                lng = this.$store.state.potholes[i].longitude;
                const marker = new window.google.maps.Marker({
                    position: { lat, lng }
                });
                marker.setMap(this.map);
            }
            // this.isLoading = false;
        })
    }
}
</script>

<style>
#map {
    height: 900px;
}
</style>