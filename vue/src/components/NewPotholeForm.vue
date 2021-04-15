<template>
  <div class="report-pothole">
     <p id="welcome"><strong><em>Welcome!</em></strong> <br><br> Thank you for your interest in improving our city.<br> <br>• To see currently reported potholes, please see the map.<br> <br>• Pothole severity is as follows: </p>
      <div id="green-marker" class="top-marker">
        <img class = "marker" src="@/assets/1.png" alt = "logo">
        <p> Least Severe (1)</p>
        <br>
      </div>
       <div id="yellow-marker">
        <img class = "marker" src="@/assets/2.png" alt = "logo">
        <p> Medium Severity (2)</p>
        <br>
      </div>
       <div id="orange-marker">
        <img class = "marker" src="@/assets/3.png" alt = "logo">
        <p> High Severity (3)</p>
        <br>
      </div>
     <div id="red-marker" class="bottom-marker">
        <img id="red-marker-image" class = "marker" src="@/assets/4.png" alt = "logo"> 
        <p>  Maximum Severity (4 - 5)</p>
        <br>
      </div>
      <p>• To report a pothole, please fill out the address in the field below. <br><br> Our team will address the pothole as soon as we can.</p>
      <p><strong>Report a Pothole:</strong></p>
      <form v-show="!showConfirmForm" v-on:submit.prevent class="pothole-form">
          <label for="address"> Address: </label>
          <input id="inputfield" type="text" v-model="userAddress" />
          <button id="buttonform" v-on:click="showConfirmForm = !showConfirmForm" class="btn-submit">Report</button>
      </form>
      <div v-show="showConfirmForm" id="confirmation">
          Is this the right address?
          {{ userAddress }}
          <button id="buttonyes" v-on:click="submitForm">Yes</button>
          <button id="buttonno" v-on:click="resetPothole">No</button>
      </div>
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
                dateReported: null,
                visible: true
            },
            userAddress: null,
            showConfirmForm: false
        }
    },
    methods:{
        submitForm() {

            let addressLat = null;
            let addressLng = null;
            let tempPothole = this.pothole;
            let addToStore = this.updateStore;
            let createDate = this.makeDate;
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
                                tempPothole.dateReported = createDate();

                                PotholeService
                                    .reportPothole(tempPothole)
                                    .then(response => {
                                        if(response.status === 201 || response.status === 200) {
                                            addToStore();
                                        }
                                    })
                                    .catch(error => {
                                        window.alert("Error: " + error.message);
                                    });
                            }
                        }
                    }
                );
            }
        },
        updateStore() {
            this.$store.commit('ADD_POTHOLE', this.pothole);
            this.showConfirmForm = !this.showConfirmForm;
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
        resetPothole() {
            this.pothole.address = null;
            this.showConfirmForm = !this.showConfirmForm;
        }
    }
}
</script>

<style>
#inputfield{
    border: solid black 3px;
    width: 86%;

}
p{
    font-size: 20px;
}
.marker{
    height: 20px;
    width: auto;
}
.report-pothole{
    font-family: Helvetica, sans-serif;
}
#buttonform{
    width: 90%;
}
#buttonyes{
    width: 91%;
}
#buttonno {
    width: 91%;
}
#welcome{
    width: 94%;
}

#inputfield{
    border: solid black 3px;
    width: 86%;
}
#red-marker, #green-marker, #yellow-marker, #orange-marker{
    display: flex;
    justify-content: left;
    align-items: center;
}
#red-marker-image{
    padding-right: 4px;
    padding-left: 5px;
}
.top-marker{
    border-top: 3px solid rgb(208, 208, 208);
}
.bottom-marker{
    border-bottom: 3px solid rgb(208, 208, 208);
}

</style>