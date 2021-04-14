<template>
  <div id="app">
    <div id="spinner-div" v-if="isLoading">
      <spinner > </spinner>
    </div>
    <div id="flexbox" v-if="$store.state.token != ''">
      <div id="icon">
        <img class = "image" src="@/assets/blacklogo.jpg" alt = "logo">
        <h1 id="title">PHILLY POTHOLE TRACKER</h1>
      </div>
      <div id="nav">
        <router-link class="top"  v-bind:to="{ name: 'home' }">Home </router-link>
        <p class="top">|</p>
        <router-link class="top" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"> Logout</router-link>
      </div>
    </div>
    <router-view id="router-view" />
  </div>
</template>

<script>
import Spinner from "@/components/Spinner.vue";
// import {mapState} from 'vuex'
export default{
  components: {
     Spinner 
  },
  data(){
    return {
      isLoading: false
    }
  }
};
</script>

<style>
  #flexbox{
    display:flex;
    justify-content: space-between;
    padding-top: 10px;
    padding-right: 10px;
    padding-bottom: 5px;
    margin: 0 auto;
    padding-inline-start: 10px;
    z-index: 5;
    height: 100px;
    background-color: white;
    align-items: center;
    border-bottom: solid 5px rgb(230, 226, 228);
  }
  #nav {
    display: flex;
    /* justify-content: space-evenly; */
  }

.top {
font-family: Helvetica, sans-serif;
font-size: 25px;
font-weight: bold;
color:black;
text-decoration: none;
vertical-align: middle;
margin-top: 50px;
}

  p.top {
    margin-left: 5px;
    margin-right: 5px;
  }

#icon{
  display: flex;
  align-items: center;
  padding-left: 8px;
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

.top:active{
  color:rgb(92, 91, 91);
}


@import url('https://fonts.googleapis.com/css2?family=Kanit:wght@900&display=swap');
#title{
  font-family: "Kanit", Helvetica, sans-serif;
  font-size:45px;
  padding-left: 10px;
}

@media only screen and (max-width: 600px) {
  #app {
    display: flex;
    flex-direction: column;
    

  }
  #flexbox {
    
  }
  
  #router-view {
    height: 100%;
  }

  #title {
    font-size: 25px;
  }

  #nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .top {
    font-size: 20px;
  }

  p.top {
    margin-bottom: 0;
    margin-left: 5px;
    margin-right: 5px;
  }

  

}
</style>