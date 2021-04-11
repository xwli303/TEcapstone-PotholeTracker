<template>
  <div id="employee-menu-container">
    <form id="filter-form" v-on:submit.prevent v-show="showFilterForm == true">
      <p id="filter-potholes"><strong>Filter Potholes by:</strong></p>
      <label for="status"> Status </label>
      <select id="status" v-model="statusFilter">
        <option v-for="option in StatusOptions" v-bind:value="option.value" :key="option.value">
        {{option.text}}
        </option>
      </select>
      <br>
      <label for="severity">Severity </label>
      <select id="severity" v-model="severityFilter">
        <option v-for="option in severityOptions" v-bind:value="option.value" :key="option.value">
        {{option.text}}
        </option>
      </select>
      <button id="filter" @click="filterPotholes" class="submit filter-button"> Filter </button>
    </form>
    <div id="main-list" v-show="showFilterForm == true">
      <ul id="employee-buttons-list">
        <!-- <button id="employee-button" v-for="pothole in $store.state.filteredPotholes" -->
        <button id="employee-button" 
          v-for="pothole in $store.state.potholes"
          v-bind:key="pothole.id"
          v-show="pothole.visible === true"
          @click="showPopulatedPotholeForm(pothole.id)" >
          ID: {{ pothole.id }} | Address: {{ pothole.address }}
        </button>
      </ul>
    </div>

    <!-- this is the form that updates the pothole -->
    <form id="updatePotholeForm" v-if="showFilterForm == false" v-on:submit.prevent="submitUpdatedPothole(updatedPothole[0])">
      <div v-for="pothole in updatedPothole" v-bind:key="pothole.id">
      <h3>Update Pothole Information</h3>
        <p>Pothole ID: {{pothole.id}}</p>
        <p>Address: {{pothole.address}}</p>
        <p>Lat/Long: {{pothole.latitude}}, {{pothole.longitude}}</p>
        <p>Date Reported: {{pothole.dateReported}}</p>
      
        <label for="status">Status</label>
        <select name="status" id="update-status" v-model="pothole.statusCode">
          <option v-for="option in StatusOptions" v-bind:value="option.value" :key="option.value">
          {{option.text}}
          </option>
        </select>
        <br>
        <label for="severity-label">Severity</label>
        <select name="severity" id="update-severity" v-model="pothole.severity">
          <option v-for="option in severityOptions" v-bind:value="option.value" :key="option.value">
          {{option.text}}
          </option>
        </select>
        <br>
        <label for="date-inspected"> Date Inspected </label>
        <input type="text" id="date-inspected" v-model="pothole.dateInspected"/>
        <br>
        <label for="date-repaired"> Date Repaired </label>
        <input type="text" id="date-repaired" v-model="pothole.dateRepaired"/>
      </div>
      <button class="submit">Update Pothole</button>
    </form>
    <button v-show="databaseUpdated" @click="databaseUpdated=false">{{dbTextUpdate}}</button>
  </div>
</template>

<script>
import PotholeService from "@/services/PotholeService";
export default {
    name: 'employee-menu',
    showMainList: true,
    
    data() {
      return {
      updatedPothole: {
        address: null,
        dateInspected: null,
        dateRepaired: null,
        dateReported: null,
        id: 1,
        latitude: null,
        longitude: null,
        severity: null,
        statusCode: null,
        user_id: null,
        visible: true },
      StatusOptions: [
        { text: '', value: ''},
        { text: 'Reported', value: '1'},
        { text: 'Inspected', value: '2'},
        { text: 'Repaired', value: '3'}
      ],
      severityOptions:[
        { text: '', value: ''},
        { text: '5', value: '5'},
        { text: '4', value: '4'},
        { text: '3', value: '3'},
        { text: '2', value: '2'},
        { text: '1', value: '1'}
      ],
      statusFilter:'',
      severityFilter:'',
      showFilterForm: true,
      databaseUpdated: false,
      dbTextUpdate:''
      }
    },
    methods: { 
      filterPotholes() {  

        const tempStatus = this.statusFilter;
        const tempSeverity = this.severityFilter;

        // Below creates a new, seperate array of potholes
        const potholesToReturn = JSON.parse(JSON.stringify(this.$store.state.potholes));

        this.$store.commit('DELETE_ALL_POTHOLES');

        potholesToReturn.forEach(pothole => {
          // if status and severity are empty strings = all potholes are visible
          ( ((tempStatus == pothole.statusCode) || (tempStatus == '')) 
            && ((tempSeverity == pothole.severity) || tempSeverity == '') ) 
            ? pothole.visible = true : pothole.visible = false;
        });

        for (let i = 0; i < potholesToReturn.length; i++) {
          this.$store.commit('ADD_POTHOLE', potholesToReturn[i]);
        }
      },
      showPopulatedPotholeForm(potholeId){
        const returnedPothole = this.$store.state.potholes.filter(pothole => 
        pothole.id == potholeId);
        this.updatedPothole = returnedPothole;
        this.showFilterForm = false;
      },
      submitUpdatedPothole(pothole){
        pothole.severity = parseInt(pothole.severity);
        pothole.statusCode = parseInt(pothole.statusCode);
        let filter = this.showFilterForm;
        let db = this.databaseUpdated;
        let text = this.dbTextUpdate;
       PotholeService.updatePothole(pothole).then((response) =>{ 
        if(response.status == 200){
          filter = !filter;
          db = !db;
          text = "Pothole Updated!";
          console.log(text);
        }
       })
       .catch(error => {
         filter = !filter;
         db = !db;
         text = "There was an error: " + error.response.statusText;
         console.log(text);
       })
      }
    },

}
</script>

<style>
div {
  font-family: Helvetica, sans-serif;
  
}

.employee-menu-container{
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  display: inline-block;
  z-index: 1;
}

#employee-buttons-list{
  padding-left: 0px;
  width: 93%;

}
#employee-button{
  width: 98%;
  height: auto;
  display: inline-block

}
.filter-button{
  width: 92%;
}

#severity{
  width: 30%;
}
#status{
  width: 30%;
}

</style>