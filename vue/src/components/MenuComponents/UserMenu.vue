<template>
  <div id="form">
    <button @click="showList = !showList">
      {{showList == false ? 'Show Pothole Status Tracker' : 'Return to Submission Form'}}
    </button>
    <new-pothole-form v-show="showList == false" />
    <pothole-list v-show="showList == true" />
  </div>
</template>

<script>
import NewPotholeForm from '@/components/NewPotholeForm';
import PotholeList from '../PotholeList.vue';
export default {
  name: 'user-menu',
  components: {
    NewPotholeForm,
    PotholeList
  },
  data() {
    return {
      showList: false
    }
  }

}
</script>

<style>
button{
    width: 90%;
}
</style>