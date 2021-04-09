<template>
  <div class="report-pothole">
      <form v-show="!dbUpdated" v-on:submit.prevent="submitForm" class="pothole-form">
          <h2>Report a Pothole</h2>
          <label for="address" > Address: </label>
          <br>
          <input id="inputfield" type="text" v-model="address" />
          <button v-on:click="dbUpdated = !dbUpdated" class="btn-submit">Report</button>
      </form>
      <form v-show="dbUpdated" v-on:submit.prevent="updateStore" id="confirmation-form">
          Is this the right address?
          {{ address }}
          <button v-on:click="dbUpdated = !dbUpdated" class="btn-submit">Yes</button>
      </form>
  </div>
</template>

<script>
import PotholeService from "../services/PotholeService";
export default {
    name:"pothole-form",
    data(){
        return {
            pothole:{
                user_id: this.$store.state.user.id,
                latitude: 21,
                longitude: 42,
                severity: 1,
                statusCode: 1,
                dateReported: '2021-04-07'
            },
            address: null,
            dbUpdated: false
        }
    },
    methods:{
        submitForm() {
            let addressLat = null;
            let addressLng = null;
            let tempPothole = this.pothole;
            let geocoder = new window.google.maps.Geocoder();
            if ( this.address != null ) {
                geocoder.geocode( {'address': this.address}, 
                    function(results, status) {
                        if (status == 'OK') {
                            if (results[0]) {
                                let potholeLatLng = results[0].geometry.location;
                                addressLat = potholeLatLng.lat();
                                addressLng = potholeLatLng.lng();
                                tempPothole.latitude = addressLat;
                                tempPothole.longitude = addressLng;
                                PotholeService
                                    .reportPothole(tempPothole)
                                    .then(response => {
                                        if(response.status === 201 || response.status === 200) {
                                            console.log(response.status);
                                        }
                                    })
                                    .catch(error => {
                                        console.log(error); 
                                    });
                            }
                        }
                    }
                );
            }
        },
        updateStore() {
            this.$store.commit('ADD_POTHOLE', this.pothole);
        }
    }
}
</script>

<style>
#inputfield{
    border: solid black 3px;
    width: 86%;

}
.report-pothole{
    font-family: Helvetica, sans-serif;
}
button{
    width: 94%;
}
h2{
    font-size: 20px;
}
</style>