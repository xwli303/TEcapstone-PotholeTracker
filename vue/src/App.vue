<template>
  <div id="app">
    <div id="flexbox" v-if="$store.state.token != ''">
      <div id="icon">
        <h2>our image</h2>
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
    padding-right: 30px;
    margin: 0 auto;
    padding-inline-start: 10px;
    z-index: 1;
    height: 100px;
    background-color: white;

  }
/* unvisited link */
#nav {

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

/* mouse over link */
.top:hover {
color: grey;
text-decoration: none;
}

</style>