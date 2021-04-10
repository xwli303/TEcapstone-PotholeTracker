<template>
  <div>
    <form class="dropdown">
      <label for="status"> Status </label>
      <select class="dropdown-content">
        <option value="Reported">Reported</option>
        <option value="Inspected">Inspected</option>
        <option value="Repaired">Repaired</option>
      </select>
    </form>
   <br>
    <form class="dropdown">
      <label for="severity">Severity</label>
      <select class="dropdown-content">
        <option value="5">5</option>
        <option value="4">4</option>
        <option value="3">3</option>
        <option value="2">2</option>
        <option value="1">1</option>
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


</style>