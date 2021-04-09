<template>
  <div id="app">
    <div id="flexbox" v-if="$store.state.token != ''">
      <div id="icon">
        <img class = "image" src="@/assets/blacklogo.jpg" alt = "logo">
        </div>
      <div id="nav">
      <router-link class="top"  v-bind:to="{ name: 'home' }">Home | </router-link>
      <router-link class="top" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"> Logout |</router-link>
      <router-link class="top" v-bind:to="{ name: 'pothole-view'}"> List </router-link>
      </div>
      
    </div>
    <router-view />
  </div>
</template>

<style>
  #flexbox{
    display:flex;
    justify-content: space-between;
    
    margin: 0 auto;
    padding-inline-start: 10px;
    z-index: 1;
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

.image{
  height: 100px;
  width: auto;
}
/* mouse over link */
.top:hover {
color: grey;
text-decoration: none;
}

</style>