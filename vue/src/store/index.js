import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    potholes: [],
  
    filter: 0
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    ADD_POTHOLE(state, pothole) {
      state.potholes.push(pothole);
    },
    UPDATE_FILTER(state, filter){
      state.filter = filter;
    },
    // SET_FILTERED_POTHOLES(state){
    //   state.filteredPotholes = state.potholes;
    // },
    SET_POTHOLES(state, potholes){
      state.potholes = potholes;
    },
    DELETE_LAST_POTHOLE(state) {
      state.potholes.pop();
    },
    DELETE_ALL_POTHOLES(state) {
      state.potholes = [];
    },
    GET_POTHOLE_BY_ID(state, potholeId){
      return state.potholes.filter(pothole => 
        pothole.id == potholeId);
    }
  }
})
