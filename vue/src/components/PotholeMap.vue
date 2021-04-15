<template>
    <div id="map-container">
        <div id="overlay" v-if="isLoading">
            <div id="text">
                <p >Loading...</p>
            </div>
            <div id=#loader>
                <spinner> </spinner>
            </div>
        </div>
        <div v-if="mobile" id="mobile-instructions">
            <p>Tap on the screen to report a new pothole!</p>
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
    name: "PotholeMap",
    components: { 
        MapMarker, 
        Spinner
    },
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
    computed: {
        mobile() {
            return (screen.width <= 600);
        } 
    },
    mounted() {
        this.wait();
        const loader = new Loader({
            apiKey: "AIzaSyDRcIpT0bxsU80JISszJUMH2uyxYpZ2I-w",
            version: "weekly"
        });
        loader.load().then(() => {
            this.map = new window.google.maps.Map(document.getElementById("map"), {
                center: { lat: 39.9528, lng: -75.1635 },
                zoom: 13,
                mapTypeControl: false,
                fullscreenControl: false,
            });
            let report = false;
            function CenterControl(controlDiv) {

                const controlUI = document.createElement("div");
                controlUI.style.backgroundColor = "#fff";
                controlUI.style.border = "2px solid #000";
                controlUI.style.borderRadius = "5px";
                controlUI.style.boxShadow = "0 2px 6px rgba(0,0,0,.3)";
                controlUI.style.cursor = "pointer";
                controlUI.style.marginTop = "8px";
                controlUI.style.marginBottom = "22px";
                controlUI.style.textAlign = "center";
                controlUI.title = "Click to Report Pothole";
                controlDiv.appendChild(controlUI);
                
                const controlText = document.createElement("div");
                controlText.style.color = "red";
                controlText.style.fontFamily = "Roboto,Arial,sans-serif";
                controlText.style.fontSize = "20px";
                controlText.style.fontWeight = "bold"
                controlText.style.lineHeight = "38px";
                controlText.style.paddingLeft = "5px";
                controlText.style.paddingRight = "5px";
                controlText.innerHTML = "Report Pothole";
                controlUI.appendChild(controlText);
                
                controlUI.addEventListener("click", () => {
                    report = true;
                    console.log("REPORT");

                });
            }
            this.map.addListener("rightclick", (mapsMouseEvent) => {
                let infoString = mapsMouseEvent.latLng.lat() + ", " + mapsMouseEvent.latLng.lng();
                window.alert(infoString);
            });
            if(screen.width <= 600) {
                const centerControlDiv = document.createElement("div");
                CenterControl(centerControlDiv);
                this.map.controls[window.google.maps.ControlPosition.TOP_CENTER].push(centerControlDiv);            
                let addToStore = this.updateStore;
                this.map.addListener("click", (mapsMouseEvent) => {
                    if (report) {
                        report = false;
                        this.pothole.latitude = mapsMouseEvent.latLng.lat();
                        this.pothole.longitude = mapsMouseEvent.latLng.lng();
                        this.pothole.dateReported = this.makeDate();
                        this.pothole.address="MOBILE REPORT";
                        PotholeService
                            .reportPothole(this.pothole)
                            .then(response => {
                                if(response.status === 201 || response.status === 200) {
                                    addToStore();
                                }
                            })
                            .catch(error => {
                                window.alert("Error: " + error.message);
                            });
                    }      
                });    
            }
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
        },
         updateStore() {
            this.$store.commit('ADD_POTHOLE', this.pothole);
            this.showConfirmForm = !this.showConfirmForm;
        },
        makeAddress(mapsMouseEvent){
            const newStringMaker = this.copyString;
            let geocoder = new window.google.maps.Geocoder(); 
                    geocoder.geocode( {location: mapsMouseEvent.latLng}, (results) => {
                                const placeString = results[0].address_components[0].long_name + " " +
                                results[0].address_components[1].long_name;
                                newStringMaker(placeString);
                            });   // end geocode 
                            
        },
        copyString(string){
            this.newAddress = string;
        }
    }
}
</script>

<style>
#map {
    height: 87.6vh;
    width: auto;
    margin-left: 10px;
    margin-top: 0px;
    
}

#overlay{
    position: absolute;
    margin-top:110px;
    top:0;
    left: 0;
    width:100%;
    height:100%;
    z-index: 99;
    background-color: white;
    display: flex;
    flex-direction: column;
    text-align: center;
    justify-content:center;
 
}

#loader{
    display: table-cell;
    vertical-align: middle;
    position: relative;

}

#text{
    position: relative;
}
#mobile-instructions {
    visibility: hidden;
}

@media only screen and (max-width: 600px) {
    #map-container{
        width:100%;
        height: 100%;
        overflow: hidden;
    }
    #map {
        margin-left: 0;
        width: 100%;
        height: 75vh;
        height: calc(var(--vh, 1vh) *76);
    }
    #mobile-instructions {
        visibility: visible;
        display: flex;
        justify-content: center;
        background-color: white;
        height: 8vh;     
    }
}
</style>