<template>
  <div class="report-pothole">
      <form v-on:submit.prevent="submitForm" class="pothole-form">
          <label for="latitude"> Latitude </label>
          <input type="text" v-model="pothole.latitude"/>
          <br>
          <label for="longitude">Longitude</label>
          <input type="text" v-model="pothole.longitude"/>
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
                latitude: 0,
                longitude: 0,
                severity: 1,
                statusCode: 1,
                dateReported: '2021-04-07'
            }
        }
    },

    methods:{
        submitForm(){
            PotholeService
                .reportPothole(this.pothole)
                .then(response => {
                    if(response.status === 201) {
                        this.$router.push('/report-pothole');
                    }
                })
                .catch(error => {
                    this.handleErrorResponse(error); 
                });
        }
    }

}
</script>

<style>

</style>