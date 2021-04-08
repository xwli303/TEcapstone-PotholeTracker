<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'pothole-view'}"> List </router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
  #nav{
    position: absolute;
    z-index: 1;
    height: 15%;
    background-color: aqua;
  }
  
</style>