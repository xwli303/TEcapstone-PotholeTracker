import axios from 'axios';

export default{
    listPotholes(){
        return axios.get('/all-potholes');
    },

    listAllPotholesOrdered(orderBy){
        return axios.get(`/all-potholes/ordered-by/${orderBy}`);
    },

    reportPothole(pothole){
        return axios.post('/report-pothole', pothole);
    }, 

    getPotholeById(potholeId){
        return axios.get(`/all-potholes/get-pothole-by-id/${potholeId}`);
    },

    updatePothole(potholeId){
        return axios.put(`/all-potholes/update-pothole/${potholeId}`);
    },

    deletePothole(potholeId){
        return axios.delete(`/delete-pothole/${potholeId}`);
    }




}