<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <pothole-list> </pothole-list>

  </div>
</template>

<script>
import PotholeList from '../components/PotholeList.vue';
export default {
  name: "home",
  components: { 
    PotholeList 
    }
};
</script>

<style>
 
</style>
