<template>
<div>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <img class = "login-image" src="@/assets/blacklogo.png" alt = "logo">
      <h1 class="h3 mb-3 font-weight-normal philly">PHILLY <br/> POTHOLE <br/> TRACKER</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <br class="break-to-hide">
      <input
        type="text"
        id="username"
        class="form-control"
        v-model="user.username"
        required
        autofocus
      />
      <br class="break-to-hide">
      <label for="password" class="sr-only">Password</label>
      <br class="break-to-hide">
      <input
        type="password"
        id="password"
        class="form-control"
        v-model="user.password"
        required
      />
      <br>
      <button id="button1" type="submit">Sign in</button>
      <br id="button-break">
      <button id="button2" @click="guestLogin">Guest Account</button>
      <br>
      <router-link id = "link" :to="{ name: 'register' }">Need an account?</router-link>
    </form>
  </div>
</div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    guestLogin() {
      this.user.username = "guest";
      this.user.password = "guest";
      this.login();
    }
  }
};
</script>

<style>
  body{
    margin: 0;
    padding: 0;
  }
  #login{
    background: url('~@/assets/philadelphia_skyline.jpg');
    background-size: cover;
    width: 100%;
    height: 100%;
    position: fixed;
    background-repeat: no-repeat;
    
  }
  .text-center{
    margin: 0 auto;
    text-align: center;
  }
  @import url('https://fonts.googleapis.com/css2?family=Kanit:wght@900&display=swap');
  .philly{
    font-family: "Kanit", Helvetica, sans-serif;
    font-size: 38px;
    margin-top: 0;
    margin-bottom: 0;
  }
  .form-signin{
    background-color: rgba(194, 190, 190, 0.55);
    position: left;
    top:50%;
    left: 50%;
    width: 300px;
    border-radius: 15px;
    padding: 30px 20px 50px 20px;
    margin-left: 30px;
    margin-top: 60px;
    font-family: Helvetica, sans-serif;
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
    width: 100px;
    height:30px;
    border-radius: 3px;
    border:none;
    margin:10px 0px 10px 0px;
    
  }
  #button1{
    width: 63%;
  }
  #button2{
    width: 63%;
  }


  .login-image{
  height: 100px;
  width: auto;
  align-self: center;
}

  #link{
    color:black;
    text-decoration: none;
  }
  #link:hover {
    color: grey;
    text-decoration: none;
  }

  @media only screen and (max-width: 600px) {

    #login {
    display: flex;
    justify-content: center;
    align-items: center;
    background: url('~@/assets/philadelphia_skyline.jpg');
    background-size: cover;
    width: 100%;
    height: 100%;
    position: fixed;
    background-repeat: no-repeat;
    }
    .form-signin{
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      background-color: rgba(194, 190, 190, 0.808);
      position: left;
      top:50%;
      left: 50%;
      width: 300px;
      height: 70%;
      border-radius: 15px;
      padding: 30px 20px 50px 20px;
      font-family: Helvetica, sans-serif;
      margin: 10px 10px 10px 10px;
  }
  .philly{
    font-size: 30px;
  }
  .login-image {
    height: 70px;
  }
  #username,#password {
    margin-bottom: 0;
  }
  #button1,#button2 {
    margin: 0 0 0 0;
  }
  .break-to-hide {
    display: none;
  }
  label {
    margin: 5px 0;
  }
}

</style>
