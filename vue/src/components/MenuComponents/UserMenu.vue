<template>
  <div id="form">
    <new-pothole-form v-show="showReportPothole" />
    <p v-show="showReportPothole">Click below to see a list of all potholes and their current status</p>
    <button class="user-menu-button" @click="swapReportAndList" v-show="showFileDamageClaim == false">
      {{showPotholeList == false ? 'Show Pothole Status Tracker' : 'Return to Pothole Reporting'}}
    </button>
    <p v-show="showReportPothole">Click below to open a form to submit a pothole-related damage claim</p>
    <button class="user-menu-button" @click="swapReportAndClaim" v-show="showPotholeList == false">
      {{showFileDamageClaim == false ? 'Show Damage Claim Form' : 'Return to Pothole Reporting'}}
    </button>
    <pothole-list v-show="showPotholeList" />
    <form id="damage-claim-form" @submit.prevent="submitClaim" v-show="showFileDamageClaim">
      <h2>Submit a damage claim to the city of Philadelphia:</h2>
      <label for="name">Name: </label><br />
      <input type="text" name="name" /><br />
      <label for="address">Address: </label><br />
      <input type="text" name="address" placeholder="123 Market Street, Philadelphia, PA" /><br />
      <label for="phone">Phone: </label><br />
      <input type="tel" name="phone" placeholder="xxx-xxx-xxxx" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required /><br />
      <label for="date">Date of Incident: </label><br />
      <input type="date" name="date" /><br />
      <label for="details">Details: </label><br />
      <textarea name="details" rows="10" cols="40" />
      <button type="submit">Submit Claim</button>
    </form>
  </div>
</template>

<script>
import NewPotholeForm from '@/components/NewPotholeForm';
import PotholeList from '../PotholeList.vue';
export default {
  name: 'user-menu',
  components: {
    NewPotholeForm,
    PotholeList
  },
  data() {
    return {
      showPotholeList: false,
      showReportPothole: true,
      showFileDamageClaim: false
    }
  },
  methods: {
    swapReportAndList() {
      this.showPotholeList = !this.showPotholeList;
      this.showReportPothole = !this.showReportPothole;
    },
    swapReportAndClaim() {
      this.showReportPothole = !this.showReportPothole;
      this.showFileDamageClaim = !this.showFileDamageClaim;
    },
    submitClaim() {
      window.alert('Claim Submitted!');
      this.swapReportAndClaim();
    }
  }

}
</script>

<style>
.user-menu-button {
  width: 90%;
}
input {
  width: 90%;
}
</style>