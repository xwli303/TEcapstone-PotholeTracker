<template>
  <div id="app">
    <spinner v-if="showLoading"> </spinner>
    <div id="flexbox" v-if="$store.state.token != ''">
      <div id="icon">
        <img class = "image" src="@/assets/blacklogo.jpg" alt = "logo">
        <h1 id="title">PHILLY POTHOLE TRACKER</h1>
        </div>
      <div id="nav">
      <router-link class="top"  v-bind:to="{ name: 'home' }">Home | </router-link>
      <router-link class="top" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"> Logout</router-link>
      <router-link class="top" v-bind:to="{ name: 'pothole-view'}" v-if="$store.state.user.authorities[0].name === 'ROLE_EMPLOYEE'"> | List </router-link>
      </div>
      
    </div>
    <router-view />
  </div>
</template>
<script>
import Spinner from "@/components/Spinner.vue";
import {mapState} from 'vuex'
export default{
  components: {
     Spinner 
  },
  computed: {
    ...mapState({
      showLoading: state => state.isLoading
    })
  }  
};


</script>


<style>
  #flexbox{
    display:flex;
    justify-content: space-between;
    padding-top: 10px;
    padding-right: 10px;
    margin: 0 auto;
    padding-inline-start: 10px;
    z-index: 5;
    height: 100px;
    background-color: white;
    align-items: center;
  }

.top {
font-family: Helvetica, sans-serif;
font-size: 20px;
font-weight: bold;
color:black;
text-decoration: none;
vertical-align: middle;
margin-top: 50px;
}

#icon{
  display: flex;
  align-items: center;
}

.image{
  height: 100px;
  width: auto;
}
/* mouse over link */
.top:hover {
color: grey;
text-decoration: none;
}
@import url('https://fonts.googleapis.com/css2?family=Kanit:wght@900&display=swap');
#title{
  font-family: "Kanit", Helvetica, sans-serif;
  font-size:30px;
  padding-left: 10px;
}

</style>