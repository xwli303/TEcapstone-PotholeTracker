<template>
  <div class="report-pothole">
      <form v-on:submit.prevent="submitForm" class="pothole-form">
          <label for="latitude"> Latitude </label>
          <input type="text"/>
          <br>
          <label for="longitude">Longitude</label>
          <input type="text"/>
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
            latitude:39.950,
            longitude: -75.150
        }
    },

    methods:{
        submitForm(){
            const newPothole = {
                latitude: 39.950,
                longitude: -75.150
            };

            PotholeService
                .reportPothole(newPothole)
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