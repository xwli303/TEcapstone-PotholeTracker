<template>
  <div id="employee-menu-container">
    <p id="filter-potholes"><strong>Filter Potholes by:</strong></p>
    <form class="status-form" v-on:submit.prevent>
      <label for="status"> Status </label>
      <select id="status" v-model="statusFilter">
        <option v-for="option in StatusOptions" v-bind:value="option.value" :key="option.value">
        {{option.text}}
        </option>
      </select>
      <!-- <button id="filter" @click="filterPotholes" class="submit"> Filter </button> -->
      <!-- <ul>
            <button v-for="pothole in $store.state.filteredPotholes" v-bind:key="pothole.id">{{pothole.address}}</button>
      </ul> -->
    </form>
    
   <br>
    <form class="severity-form" v-on:submit.prevent>
      <label for="severity">Severity </label>
      <select id="severity" v-model="severityFilter">
        <option v-for="option in severityOptions" v-bind:value="option.value" :key="option.value">
        {{option.text}}
        </option>
      </select>
      <button id="filter" @click="filterPotholes" class="submit filter-button"> Filter </button>
    </form>
    <div id="main-list">
      <ul id="employee-buttons-list">
        <!-- <button id="employee-button" v-for="pothole in $store.state.filteredPotholes" -->
        <button id="employee-button" 
          v-for="pothole in $store.state.potholes"
          v-bind:key="pothole.id"
          v-show="pothole.visible === true">
          ID: {{ pothole.id }} | Address: {{ pothole.address }}
        </button>
      </ul>
    </div>

    <!-- this is the form that updates the pothole -->
    <div id="updatePotholeForm">
      <h3>Update Pothole Information</h3>
      <form id="update-status-form">
        <label for="status">Status</label>
        <select name="status" id="update-status">
          <option v-for="option in severityOptions" v-bind:value="option.value" :key="option.value">
          {{option.text}}
          </option>
        </select>
      </form>
      <form id="update-severity-form">
        <label for="severity-label">Severity</label>
        <select name="severity" id="update-severity" >
          <option v-for="option in severityOptions" v-bind:value="option.value" :key="option.value">
          {{option.text}}
          </option>
        </select>

      </form>
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
        severityFilter:''
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

      }
    },
}
</script>

<style>
div {
  font-family: Helvetica, sans-serif;
  
}

#employee-menu-container{
  position: relative;
  
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
/* #filter-potholes{
  padding-top: 100px;
} */
#severity{
  width: 30%;
}
#status{
  width: 30%;
}

</style>