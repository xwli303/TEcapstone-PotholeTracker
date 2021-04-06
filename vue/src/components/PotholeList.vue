<template>
  
</template>

<script>
import PotholeService from "../services/PotholeService";
export default {
    name: "pothole-list",
    data(){
        return{
            potholes:[]
        };
    },

    created(){
        PotholeService.listPotholes().then((response) => {
            this.potholes = response.data;
        })
    }
   
}
</script>

<style>

</style>