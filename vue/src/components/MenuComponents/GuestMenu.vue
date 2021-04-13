<template>
  <div id="container">
    <p id="welcome">Welcome! <br><br> Thank you for your interest in improving our city.<br> <br>• To see currently reported potholes, please see the map.<br> <br>• Pothole severity is as follows: </p>
      <div id="green-marker">
        <img class = "marker" src="@/assets/1.png" alt = "logo">
        <p> Least Severe (1)</p>
        <br>
      </div>
       <div id="yellow-marker">
        <img class = "marker" src="@/assets/2.png" alt = "logo">
        <p> Medium Severity (2)</p>
        <br>
      </div>
       <div id="orange-marker">
        <img class = "marker" src="@/assets/3.png" alt = "logo">
        <p> High Severity (3)</p>
        <br>
      </div>
     <div id="red-marker">
        <img id ="red-marker-image" class = "marker" src="@/assets/4.png" alt = "logo"> 
        <p>  Maximum Severity (4 - 5)</p>
        <br>
      </div>
      <p>• To report a pothole, please create an account and log in. <br><br> Our team will address the potholes as soon as we can.</p>
  </div>
</template>

<script>
export default {
    name: 'guest-menu'
}
</script>

<style>
#welcome{
  padding-right: 15px;
}
p{
    font-size: 20px;
}
#red-marker, #green-marker, #yellow-marker, #orange-marker{
    display: flex;
    justify-content: left;
    align-items: center;
}
#red-marker-image{
    padding-right: 4px;
    padding-left: 5px;
}
</style>