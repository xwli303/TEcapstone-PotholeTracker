<template>
    <div>
        
        <div id="overlay" v-if="isLoading">
            <div id="text">
                <p >Loading...</p>
            </div>
            <div id=#loader>
                <spinner> </spinner>
            </div>
            
            
        </div>
        <div id="map" ref="map">
            <map-marker v-for="pothole in $store.state.potholes" 
                v-bind:key="pothole.id"
                v-bind:dent="pothole"  
                v-bind:lat="pothole.latitude" 
                v-bind:lng="pothole.longitude"
                v-bind:visible="pothole.visible">
            </map-marker>
        </div>
    </div>
</template>

<script>
import PotholeService from "../services/PotholeService";
import { Loader } from "@googlemaps/js-api-loader";
import MapMarker from './MapMarker.vue';
import Spinner from "@/components/Spinner";
export default {
  components: { MapMarker, Spinner},
    data() {
        return {
            map: null,
            pothole:{
                user_id: this.$store.state.user.id,
                address: null,
                latitude: null,
                longitude: null,
                severity: 1,
                statusCode: 1,
                dateReported: null,
                visible: true
            },
           
            isLoading: true,
            
        }
    },
    name: "PotholeMap",
    mounted() {
        this.wait();
        const loader = new Loader({
            apiKey: "AIzaSyDRcIpT0bxsU80JISszJUMH2uyxYpZ2I-w",
            version: "weekly"
        });
        loader.load().then(() => {
            this.map = new window.google.maps.Map(document.getElementById("map"), {
                center: { lat: 39.9528, lng: -75.1635 },
                zoom: 13
            });

            // added rightclick event to map
            const infoWindow = new window.google.maps.InfoWindow({
                content: "<h2 style=\"color:blue\";>mobileReport</h2>"
                });
            this.map.addListener("rightclick",(mapsMouseEvent) => {
				console.log(mapsMouseEvent.latLng.lat());
                infoWindow.setPosition(mapsMouseEvent.latLng);
                infoWindow.open(this.map);
                {
                    //let geocoder = new window.google.maps.Geocoder();
                                this.pothole.address ="Mobile Report"; 

                              //  geocoder.geocode( {"address": mapsMouseEvent.latLng.toJSON()}, (results) =>{
                              //      console.log(results[0])
                              //  }) ;

                                this.pothole.latitude = mapsMouseEvent.latLng.lat();
                                this.pothole.longitude = mapsMouseEvent.latLng.lng();
                                this.pothole.dateReported = this.makeDate();

                                PotholeService
                                    .reportPothole(this.pothole)
                                    .then(response => {
                                        if(response.status === 201 || response.status === 200) {
                                         //addToStore();
                                           console.log("success");
                                        }
                                    })
                                    .catch(error => {
                                        window.alert("Error: " + error.message);
                                    });
                            }




            });    // end of rightclick



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
        },
        makeDate(){
            let dateString = "";
            let date = new Date();
            let year = date.getFullYear().toString();
            let month = (date.getMonth() + 1).toString();
            let day = (date.getDate() + 1).toString();
            if(date.getMonth() <10){
              dateString =  year + "-0" + month + "-" + day;
            }else{
              dateString =  year + "-" + month + "-" + day;   
            }
            return dateString;
        },

        hideLoader(){
            this.isLoading = false;
        },
        
        wait(){
            setTimeout(this.hideLoader, 2000);
            
        }
    }
}
</script>

<style>
#map {
    height: 100vh;
    width: auto;
    margin-left: 10px;
    margin-top:10px;
    
}

#overlay{
    position: absolute;
    top:0;
    left: 0;
    width:100%;
    height:120%;
    z-index: 99;
    background-color: white;
    display: flex;
    flex-direction: column;
    text-align: center;
    justify-content:
 
}

#loader{
    display: table-cell;
    vertical-align: middle;
    position: relative;

}

#text{
    position: relative;
}

@media only screen and (max-width: 600px) {
    #map {
        margin-left: 0;
        width: 100%;
        height: 500px;
    }
}
</style>