<template>
    <div>
        <div id="map" ref="map">
            <map-marker v-for="pothole in $store.state.potholes" 
                v-bind:key="pothole.id"
                v-bind:lat="pothole.latitude" 
                v-bind:lng="pothole.longitude">
            </map-marker>
        </div>
    </div>
</template>

<script>
import { Loader } from "@googlemaps/js-api-loader";
import MapMarker from './MapMarker.vue';
export default {
  components: { MapMarker },
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
        })
    },
    methods: { 
        // Credit: https://github.com/xon52/medium-tutorial-vue-maps-example/blob/master/src/App.vue lines 34-41
        getMap(callback) {
            let vm = this;
            function checkForMap() {
                if (vm.map) {
                    callback(vm.map);
                } else {
                    setTimeout(checkForMap, 200);
                }
            }
            checkForMap();
        }
    }
}
</script>

<style>
#map {
    height: 900px;
}
</style>