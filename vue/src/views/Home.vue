<template>
  <div class="home">
    <div class="home-container">
      <side-menu class="side-menu"/>
      <pothole-map class="pothole-map" />
    </div>
  </div>
</template>

<script>
import PotholeMap from "@/components/PotholeMap";
import SideMenu from "@/components/SideMenu";
import PotholeService from "@/services/PotholeService";
export default {
  name: "home",
  components: 
  { 
    PotholeMap,
    SideMenu
  },
  created(){
        PotholeService.listPotholes()
        .then((response) => {
            this.$store.state.potholes = response.data;
        })
    }
};
</script>

<style>
.home {
  height: 100%;
  width: 100%;
}
.home-container {
  display: grid;
  grid-template-areas: 
    "side-menu pothole-map pothole-map pothole-map pothole-map";
  width: 100%;
  margin: 0 auto;
}
.side-menu {
  grid-area: side-menu;
  width: 20%;
  position: absolute;
  right: 0;
  height: 100%;
  position: fixed;
  /* z-index: 1; */
  left: 0;
  top: 0;
}
.pothole-map {
  grid-area: pothole-map;
}
</style>
