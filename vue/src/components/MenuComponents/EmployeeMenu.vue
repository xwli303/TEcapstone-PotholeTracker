<template>
  <div>
    <form class="status-form">
      <label for="status"> Status </label>
      <select id="status" v-model="statusFilter">
        <option v-for="option in StatusOptions" v-bind:value="option.value" :key="option.value">
        {{option.text}}
        </option>
      </select>
    </form>
    
   <br>
    <form class="severity-form">
      <label for="severity">Severity </label>
      <select id="severity" >
        <option v-for="option in severityOptions" v-bind:value="option.value" :key="option.value">
        {{option.text}}
        </option>
      </select>
    </form>
    <div id="main-list">
      <ul>
        <button v-for="pothole in $store.state.potholes"
          v-bind:key="pothole.id">ID: {{ pothole.id }} | Address: {{ pothole.address }}</button>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
    name: 'employee-menu',
    showMainList: true,
    data() {
      return {
        updatedPothole: {
          id: null,
          user_id: null,
          address: null,
          latitude: null,
          longitude: null,
          severity: null,
          statusCode: null,
          dateReported: '2021-04-07'
        },
        StatusOptions: [
          { text: 'Reported', value: '1'},
          { text: 'Inspected', value: '2'},
          { text: 'Repaired', value: '3'}
        ],
        severityOptions:[
          {text: '5', value: '5'},
          { text: '4', value: '4'},
          { text: '3', value: '3'},
          { text: '2', value: '2'},
          { text: '1', value: '1'}
        ],
        statusFilter:0,
        severityFilter:0
      }
    },
    computed: {
      filteredPotholes(){
        // const potholeFilter = this.$store.state.filter;
        // const potholes = this.$store.state.potholes;

        return this.$store.potholes.filter(pothole => {
          return parseInt(this.statusFilter) === pothole.statusCode
        })
      }
    }
    // mounted() {
    //   let tempPotholesWithAddresses = this.potholesWithAddresses;
    //   let geocoder = new window.google.maps.Geocoder();
    //   this.$store.state.potholes.forEach((oldPothole) => {
    //     const newPothole = {
    //       id: oldPothole.id,
    //       user_id: oldPothole.user_id,
    //       address: null,
    //       severity: oldPothole.severity,
    //       statusCode: oldPothole.statusCode,
    //       dateReported: oldPothole.dateReported
    //     };
    //     const latLng = {
    //       lat: oldPothole.latitude,
    //       lng: oldPothole.longitude
    //     };
    //     geocoder.geocode( {location: latLng}, (results, status) => {
    //       if (status === "OK") {
    //         if (results[0]) {
    //           newPothole.address = results[0].formatted_address;
    //           tempPotholesWithAddresses.push(newPothole);
    //         }
    //       }
    //     });
    //   });
    // }

}
</script>

<style>
div{
  font-family: Helvetica, sans-serif;
}

</style>