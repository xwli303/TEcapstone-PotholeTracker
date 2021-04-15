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
  created() {
        PotholeService.listPotholes()
        .then((response) => {
            let newPotholeList = [];
            response.data.forEach(oldPothole => {
                const newPothole = {
                    id: oldPothole.id,
                    user_id: oldPothole.user_id,
                    address: oldPothole.address,
                    latitude: oldPothole.latitude,
                    longitude: oldPothole.longitude,
                    severity: oldPothole.severity,
                    statusCode: oldPothole.statusCode,
                    dateReported: oldPothole.dateReported,
                    dateInspected: oldPothole.dateInspected,
                    dateRepaired: oldPothole.dateRepaired,
                    visible: true
                };
                newPotholeList.push(newPothole);
            });
            this.$store.state.potholes = newPotholeList;
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
  right: 0;
  height: 87.6vh;
  position: absolute;
  /* z-index: 1; */
  left: 0;
  top: 0;
}
.pothole-map {
  grid-area: pothole-map;
  
}

@media only screen and (max-width: 600px) {
  
  .side-container {
    display: none;
  }
  .home-container {
    display: grid;
    grid-template-areas: 
      "pothole-map";
    width: 100%;
    height: 100%;
    margin: 0 auto;
  }
  .pothole-map {
    height: 100%;
  }

}

</style>
