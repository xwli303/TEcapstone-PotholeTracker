<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
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
      <br>
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
      <br>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br>
      <button type="submit">Sign in</button>
      <br>
      <button>Guest Account </button>
      <br>
      <router-link id = "link" :to="{ name: 'register' }">Need an account?</router-link>
    </form>
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
    }
  }
};
</script>

<style>
  #login{
    background: url('~@/assets/philadelphia_skyline.jpg');
    background-size: cover;
    width: 100%;
    height: 100%;
    position: fixed;
    background-repeat: no-repeat;
    
  }
  @import url('https://fonts.googleapis.com/css2?family=Kanit:wght@900&display=swap');
  .philly{
    font-family: "Kanit", Helvetica, sans-serif;
    font-size:50px;
  }
  .form-signin{
    background-color: rgba(194, 190, 190, 0.808);
    position:fixed;
    top:50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 400px;
    border-radius: 15px;
    padding: 30px 0px 50px 145px;
    font-family: Helvetica, sans-serif
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

</style>
