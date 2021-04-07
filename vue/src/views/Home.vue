<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <pothole-map />
  </div>
</template>

<script>
import PotholeMap from "@/components/PotholeMap";
export default {
  name: "home",
  components: { PotholeMap }
};
</script>

<style>
 
</style>
