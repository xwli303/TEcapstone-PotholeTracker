<template>
  <div class = "create-job">
      <h1>Create a Job</h1>
      <form v-on:submit.prevent="submitForm" class="job-form">
          <button class="btn-submit">Create</button>
      </form>
  </div>
</template>

<script>
import JobService from '../services/JobService'
// import JobService from '../services/JobService'
export default {
    name: 'new-job-form',
    data(){
        return{
            job:{
                jobId: 0,
                potholeId: 0,
                employeeId: this.$store.state.user.id,
                jobStatus: 0,
                openedDate: '',
                closedDate: ''
            }
            
        }
    },

    methods: {
        submitForm(){
            JobService
                .createJob(job)
                .then(response => {
                    if (response.status === 200 || response.status === 201){
                        console.long('Job successfully created');
                    }
                })
                .catch(error => {
                    console.log('Failed to create job')
                })
        }
    }
    
       
}
</script>

<style>

</style>