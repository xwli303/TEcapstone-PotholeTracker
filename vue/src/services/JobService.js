import axios from axios;

export default{

    listJobs(){
        return axios.get('/all-jobs')
    },

    listJobsByOrder(orderBy){
        return axios.get(`/all-jobs/ordered-by/${orderBy}`)
    },

    createJob(job){
        return axios.post('/create-job', job)
    },

    getJobById(jobId){
        return axios.get(`/get-job-by-job-id/${jobId}`)
    },

    updateJob(jobId){
        return axios.put(`/update-job/${jobId}`)
    },

    deleteJob(jobId){
        return axios.delete(`/delete-job/${jobId}`)
    }
}
