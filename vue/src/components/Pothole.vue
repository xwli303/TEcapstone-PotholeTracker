<template>
  <div class = "pothole">
      <p class="pothole-id">{{hole.id}}</p>
      <p class="pothole-location">{{hole.location}}</p>
  </div>

</template>

<script>
export default {
    name: 'pothole',
    props: ["hole"]
}
</script>

<style>

</style>