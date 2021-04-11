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
			this.$parent.getMap(map => {
				
				
			//	let infoString = "<div>" + "<h2>The is the infoDi</h2>" +"</div>"
				let iconString = "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
				if(this.dent.severity > 2){
				iconString = "http://maps.google.com/mapfiles/ms/icons/orange-dot.png"
				}
				if(this.dent.severity > 4){
				iconString = "http://maps.google.com/mapfiles/ms/icons/blue-dot.png"	
				}
				

			//	let label={ text: `${this.dent.id}`, color:"white" }  //dla 4/10
				
				let icon = {url: iconString} 
				let statusMessage = "NOT Inspected"
				if(this.dent.statusCode > 1){
					statusMessage = "Inspected on: " + `${this.dent.dateInspected}`
				}
				let message = "Reported on: " + `${this.dent.dateReported}` + "\n" +statusMessage
				
				this.marker = new window.google.maps.Marker({
					
					position: { lat: this.lat, lng: this.lng },
				//	label: label,    // dla 4/10
					map: map,
					icon: icon,
					title: message
				})
				this.marker.setVisible(this.visible); // Curly
				this.marker.setDraggable(true);   // dla 4/10
				this.marker.addListener("click", () => {
					let label={ text: "PHL", color:"black", fontSize:"14px", fontweight:"bold"}
					this.marker.setLabel(label);
					

    })
			})
		},
		beforeDestroy() {
			this.marker.setMap(null)
			window.google.maps.event.clearInstanceListeners(this.marker)
		},
		render() {
			return null
		}
	}
</script>