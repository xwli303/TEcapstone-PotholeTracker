<template>
    <div class="list">
        <div v-for="pothole in potholes" v-bind:key="pothole.id" class="pothole">
            {{pothole}}
            
        </div>

    </div>
  
</template>
    
<script>
import PotholeService from '../services/PotholeService';

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
  .list{
      padding-top: 20px;;
      font-family: Helvetica, sans-serif;
  }
</style>