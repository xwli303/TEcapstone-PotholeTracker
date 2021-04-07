<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <pothole-map />
  </div>
</template>

<script>
import PotholeMap from "@/components/PotholeMap";
import PotholeService from "@/services/PotholeService";
export default {
  name: "home",
  components: { PotholeMap },
  created(){
        PotholeService.listPotholes()
        .then((response) => {
            this.$store.state.potholes = response.data;
        })
    }
};
</script>

<style>
 
</style>
