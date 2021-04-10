<template>
  <div>
    <form class="status-form" v-on:submit.prevent>
      <label for="status"> Status </label>
      <select id="status" v-model="statusFilter">
        <option v-for="option in StatusOptions" v-bind:value="option.value" :key="option.value">
        {{option.text}}
        </option>
      </select>
      <button id="filter" @click="filterPotholes" class="submit"> Filter </button>
      <!-- <ul>
            <button v-for="pothole in $store.state.filteredPotholes" v-bind:key="pothole.id">{{pothole.address}}</button>
      </ul> -->
    </form>
    
   <br>
    <form class="severity-form" v-on:submit.prevent>
      <label for="severity">Severity</label>
      <select id="severity" v-model="severityFilter">
        <option v-for="option in severityOptions" v-bind:value="option.value" :key="option.value">
        {{option.text}}
        </option>
      </select>
      <button id="filter" @click="filterPotholes" class="submit"> Filter </button>
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
          { text: '', value: ''},
          { text: 'Reported', value: '1'},
          { text: 'Inspected', value: '2'},
          { text: 'Repaired', value: '3'}
        ],
        severityOptions:[
          { text: '', value: ''},
          {text: '5', value: '5'},
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
      //if status and severity are empty strings = return all potholes 
      filterPotholes(){  
        const tempStatus = this.statusFilter
        const tempSeverity = this.severityFilter
        let potholesToReturn = []
        this.$store.state.potholes.forEach(pothole => {
        let potholeToReturn = null
          if(tempStatus != '') {
            
            if(tempSeverity != ''){
              potholeToReturn = ((tempStatus == pothole.statusCode) && (tempSeverity == pothole.severity)) ? pothole : null
              
            } else {
              potholeToReturn = tempStatus == pothole.statusCode ? pothole : null

            }
          } else {
              if(tempSeverity != ''){
                potholeToReturn = tempSeverity == pothole.severity ? pothole : null
              } else {
                potholeToReturn = pothole
              }
            } 
            if(potholeToReturn != null) {
              potholesToReturn.push(potholeToReturn)
            }
          })
          this.$store.commit('ADD_FILTERED_POTHOLES', potholesToReturn)
      }
    },
    created(){
      this.$store.commit("SET_FILTERED_POTHOLES");
    }
}
</script>

<style>
div{
  font-family: Helvetica, sans-serif;
}
.status-form{
  padding-top: 50px;
}
#employee-buttons{
  padding-left: 0px;
  width: 93%;
}
#employee-button{
  width: 98%;
}

</style>