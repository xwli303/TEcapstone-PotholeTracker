<!--
Credit: https://github.com/xon52/medium-tutorial-vue-maps-example/blob/master/src/components/MapMarker.vue
-->
<script>
	export default {
		props: {
			lat: { type: Number, required: true },
			lng: { type: Number, required: true },
			dent:  { type: Object, required: true }, //dla 4/10
			visible: { type: Boolean, required: true}  //Curly
		},
	

		data: () => ({
			marker: null
		}),

		mounted() {
			//const myTest = this.testMethod;
			this.$parent.getMap(map => {
				
				// info window ********************
                let infoString = "<div>" + "<h2>The is the infoDiv </h2>" +
				"<h2>" + `${this.dent.address}` + "</h2>" +
				"<button onclick=\""+ `${this.testMethod()}` + "\">Press Me" +
				"</button>" +
				
				"</div>"
				console.log(infoString);
				let iconString = "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
				if(this.dent.severity > 1){
				iconString = "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png"
				}
				if(this.dent.severity > 2){
				iconString = "http://maps.google.com/mapfiles/ms/icons/orang-dot.png"
				}
				if(this.dent.severity > 3){
				iconString = "http://maps.google.com/mapfiles/ms/icons/pink-dot.png"
				}
				if(this.dent.severity > 4){
				iconString = "http://maps.google.com/mapfiles/ms/icons/purple-dot.png"	
				}
				
				
				let icon = {url: iconString} 
				let statusMessage = "NOT Inspected"
				if(this.dent.statusCode > 1){
					statusMessage = "Inspected on: " + `${this.dent.dateInspected}`
				}
				let message = "Reported on: " + `${this.dent.dateReported}` + "\n" +statusMessage
				
				this.marker = new window.google.maps.Marker({
					
					position: { lat: this.lat, lng: this.lng },
					map: map,
					icon: icon,
					title: message
				})
				this.marker.setVisible(this.visible); // Curly111
				this.marker.setDraggable(true);   // dla 4/10

				
				const infowindow = new window.google.maps.InfoWindow({
                content: infoString,
                });
				this.marker.addListener("click", () => {
					infowindow.open(map, this.marker);
					//this.testMethod();
					

    })
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