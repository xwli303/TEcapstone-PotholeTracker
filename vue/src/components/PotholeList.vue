<template>
    <div class="list">
        <div v-for="pothole in potholes" v-bind:key="pothole.id" class="pothole">
            {{pothole}}
        </div>

    </div>
  
</template>
    
<script>

export default {
    name: "pothole-list",
    data(){
        return{
            potholes:[]
        };
    }

   
}
</script>

<style>

</style>