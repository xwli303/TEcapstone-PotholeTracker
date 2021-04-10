<template>
  
</template>

<script>
import JobService from "../services/JobService";
export default {
    name: 'jobs',
    data(){
        return{
            isLoading: true
        }
    },
    methods: {
        listAllJobs(){
            JobService
                .listJobs()
                .then(response => {
                    this.$store.commit("SET_JOBS", response.data);
                    this.isLoading = false;
                })
                
        },
        getJobById(){
            JobService
                .getJobById(this.route.params.jobId)
                .then(response => {
                    this.$store.commit("SET_CURRENT_JOB", response.data);
                    this.isLoading = false;
                })
        },
        deleteJob(){
            if (
                confirm(
                    "Are you sure you want to delete this pothole? This action cannot be undone."
                )
            ){
                JobService
                .deleteJob(this.job.id)
                .then(response => {
                    if(response.status == 'OK'){
                        alert("Pothole successfully deleted");
                        this.router.push('/job-list')
                    }
                })
            }
        }
    }

}
</script>

<style>

</style>