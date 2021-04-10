<!--
Credit: https://github.com/xon52/medium-tutorial-vue-maps-example/blob/master/src/components/MapMarker.vue
-->
<script>
	export default {
		props: {
			lat: { type: Number, required: true },
			lng: { type: Number, required: true },
			dent:  { type: Object, required: true }    //dla 4/10
		},
		data: () => ({
			marker: null
		}),
		mounted() {
			this.$parent.getMap(map => {
				let label={ text: `${this.dent.id}`, color:"white" }  //dla 4/10
				this.marker = new window.google.maps.Marker({
					
					position: { lat: this.lat, lng: this.lng },
					label: label,    // dla 4/10
					map: map
				})
				this.marker.setDraggable(true);   // dla 4/10
				this.marker.addListener("click", () => {
					let label={ text: "PHL", color:"white" }
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