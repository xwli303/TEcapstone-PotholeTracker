<template>
  <div class="report-pothole">
      <form v-on:submit.prevent="submitForm" class="pothole-form">
          <label for="address"> Address </label>
          <input type="text" v-model="address" />
          <button class="btn-submit">Report</button>
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
                user_id: 1,
                latitude: 21,
                longitude: 42,
                severity: 1,
                statusCode: 1,
                dateReported: '2021-04-07'
            },
            address: null
        }
    },

    methods:{
        submitForm() {
            let addressLat = null;
            let addressLng = null;
            let tempPothole = this.pothole;
            let tempRouter = this.$router;
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
                                        if(response.status === 201) {
                                            tempRouter.push('/report-pothole');
                                        }
                                        })
                                        .catch(error => {
                                            this.handleErrorResponse(error); 
                                        });

                            }
                        }
                    }
                );
            }
        }
    }

}
</script>

<style>

</style>