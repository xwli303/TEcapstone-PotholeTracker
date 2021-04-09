<template>
  <div class="report-pothole">
      <form v-show="!dbUpdated" v-on:submit.prevent="submitForm" class="pothole-form">
          <label for="address"> Address </label>
          <input type="text" v-model="userAddress" />
          <button v-on:click="dbUpdated = !dbUpdated" class="btn-submit">Report</button>
      </form>
      <form v-show="dbUpdated" v-on:submit.prevent id="confirmation-form">
          Is this the right address?
          {{ userAddress }}
          <button v-on:click="dbUpdated = !dbUpdated" class="btn-submit">Yes</button>
      </form>
  </div>
</template>

<script>
import PotholeService from "../services/PotholeService";
export default {
    name:"pothole-form",
    data() {
        return {
            pothole:{
                user_id: this.$store.state.user.id,
                address: null,
                latitude: null,
                longitude: null,
                severity: 1,
                statusCode: 1,
                dateReported: '2021-04-07'
            },
            userAddress: null,
            dbUpdated: false
        }
    },
    methods:{
        submitForm() {
            let addressLat = null;
            let addressLng = null;
            let tempPothole = this.pothole;
            let addToStore = this.updateStore;
            let geocoder = new window.google.maps.Geocoder();
            if ( this.userAddress != null ) {
                geocoder.geocode( {'address': this.userAddress}, 
                    function(results, status) {
                        if (status == 'OK') {
                            if (results[0]) {

                                const formattedAddress = results[0].formatted_address;
                                tempPothole.address = formattedAddress;

                                let potholeLatLng = results[0].geometry.location;
                                addressLat = potholeLatLng.lat();
                                addressLng = potholeLatLng.lng();

                                tempPothole.latitude = addressLat;
                                tempPothole.longitude = addressLng;

                                PotholeService
                                    .reportPothole(tempPothole)
                                    .then(response => {
                                        if(response.status === 201 || response.status === 200) {
                                            addToStore();
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