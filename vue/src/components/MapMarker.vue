<!--
Credit: https://github.com/xon52/medium-tutorial-vue-maps-example/blob/master/src/components/MapMarker.vue
-->
<script>
import PotholeService from "../services/PotholeService";
	export default {
		props: {
			lat: { type: Number, required: true },
			lng: { type: Number, required: true },
			dent:  { type: Object, required: true }, //dla 4/10
			visible: { type: Boolean, required: true}  //Curly
		},
	

		data: () => ({
			marker: null,
		}),

		mounted() {
			//const myTest = this.testMethod;
			const role = this.$store.state.user.authorities[0].name;
			this.$parent.getMap(map => {
				
				// info window ********************
				let statusString = "Reported"
				if(this.dent.statusCode == 2){
					statusString = "Inspected"
				}
				if(this.dent.statusCode == 3){
					statusString = "Repaired"
				} 
			
                let infoString =
				"<html>" +
				"<body>" + 
				"<div >" ;
				if (role === "ROLE_EMPLOYEE") {
				infoString = infoString +
				"<h2 style=\"color:blue\";>Pothole ID: " + `${this.dent.id}` + "</h2>" ;
				}
				infoString = infoString +
				"<h2>" + `${this.dent.address}` + "</h2>" +
				"<h2>Status    : " + statusString +
				"<h2>Reported   : " + `${this.dent.dateReported}` + "</h2>"

				if(this.dent.dateInspected){
					infoString = infoString + "<h2>Inspected : " + `${this.dent.dateInspected}` + "</h2>"
				}
				if(this.dent.dateRepaired){
					infoString = infoString + "<h2>Repaired   : " + `${this.dent.dateRepaired}` + "</h2>"
				}
				if(role === "ROLE_EMPLOYEE"){
					// console.log("employee")
				
				infoString = infoString +
				"<h2 style=\"color:blue\";>Use Menu to Schedule Job</h2>" +
				"<h2 style=\"color:red\";>Right Click to Delete</h2>"
				}
                infoString = infoString +
				"</div>" +
				"<script>"+
				"function myFunction(){" +
				"console.log('xx')" +
				"}</" + "script>" +
				"</body>" +
				"</html>"

				let iconString = null
				// color coding pothole severity
				if(this.dent.severity == 1){
				iconString = "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
				}
				if(this.dent.severity == 2){
				iconString = "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png"
				}
				if(this.dent.severity == 3){
				iconString = "http://maps.google.com/mapfiles/ms/icons/orange-dot.png"
				}
			
				//GET Lat Long from MOUSE



				// title for Marker
				let icon = {url: iconString}
				let idString = "ID: " + `${this.dent.id}`
				let statusMessage = "NOT Inspected"
				if(this.dent.dateInspected){
					statusMessage = "Inspected on: " + `${this.dent.dateInspected}`
				}
				let message = idString + "\n" + "Reported on: " + 
				`${this.dent.dateReported}` + "\n" + 
				statusMessage
				
				this.marker = new window.google.maps.Marker({
					
					position: { lat: this.lat, lng: this.lng },
					map: map,
					icon: icon,
					title: message
				})
				this.marker.setVisible(this.visible); // Curly
				const isEmployee = (role === "ROLE_EMPLOYEE") ? true : false; // Curly
				this.marker.setDraggable(isEmployee);   // dla 4/10, Curly

				// infoWindow
				const infowindow = new window.google.maps.InfoWindow({
                content: infoString,
                });
			//	if(window.width>600){
				this.marker.addListener("click", () => {
					infowindow.open(this.map, this.marker);
				});
			//	}
				if (role === "ROLE_EMPLOYEE") {
					this.marker.addListener("rightclick", () => {
						if (window.confirm("Are you sure you want to delete this pothole?")) {
							PotholeService.deletePothole(this.dent.id);
							this.marker.setVisible(false);
							infowindow.close(this.map, this.marker);
						}
					})
				}
				
			})
		},
		beforeDestroy() {
			// this.marker.setMap(null)
			window.google.maps.event.clearInstanceListeners(this.marker)
		},
		render() {
			return null
		},
	methods:{
		testMethod(){
		console.log(this.dent.address);
			}
		},
	}
</script>