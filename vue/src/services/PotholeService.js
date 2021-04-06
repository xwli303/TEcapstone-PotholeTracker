import axios from 'axios';

export default{
    listPotholes(){
        return axios.get('/all-potholes');
    },

    listAllPotholesOrdered(orderBy){
        return axios.get(`/all-potholes-ordered-by/${orderBy}`);
    }


}