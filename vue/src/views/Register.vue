<template>
<div>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only">Username</label>
      <br class="mobile-hide">
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <br>
      <label for="password" class="sr-only">Password</label>
      <br class="mobile-hide">
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <br>
      <button id="button" class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      <br>
      <router-link id = "link" :to="{ name: 'login' }">Have an account?</router-link>
    </form>
  </div>
</div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
#register{
    background-image: url('~@/assets/philadelphia_skyline.jpg');
    background-size: cover;
    width: 100%;
    height: 100%;
    position: fixed;
    background-repeat: no-repeat;
  }
  .form-register{
    background-color: rgba(194, 190, 190, 0.808);
    position: left;
    top:50%;
    left: 50%;
    width: 300px;
    border-radius: 15px;
    padding: 30px 20px 50px 20px;
    margin-left: 30px;
    margin-top: 60px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif
  }

  label{
    font-weight: 500;
  }

  input{
    border-radius: 3px;
    border:none;
    height: 30px;
    width: 60%;
    margin-bottom: 10px;
    padding-left: 8px;
  }

  button{
    width:250px;
    height:30px;
    border-radius: 3px;
    border:none;
    margin:10px 0px 10px 0px;
    
  }

  #link{
    color:black;
    text-decoration: none;
  }
  #link:hover {
    color: grey;
    text-decoration: none;
  }
#button{
    width: 63%;
  }

  @media only screen and (max-width: 600px) {
    #register{
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    background-image: url('~@/assets/philadelphia_skyline.jpg');
    background-size: cover;
    width: 100%;
    height: 100%;
    position: fixed;
    background-repeat: no-repeat;
  }
    .form-register{
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    background-color: rgba(194, 190, 190, 0.808);
    position: left;
    top:50%;
    left: 50%;
    width: 90%;
    border-radius: 15px;
    padding: 30px 0 50px 0;
    margin-left: 5px;
    margin-right: 5px;
    margin-top: 0px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif
  }
  .mobile-hide {
    display: none;
  }


  }
</style>
