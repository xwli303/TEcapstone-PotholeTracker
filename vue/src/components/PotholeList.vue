<template>
    <div class="list">
        <h2>Pothole Status Tracker</h2>
        <div v-for="pothole in potholes" v-bind:key="pothole.id" class="pothole">
            <ul>
                <li>Address: {{pothole.address}}</li>
                <li>Severity: 
                    {{pothole.severity == 1 ? 'Bump In The Road' : ''}}
                    {{pothole.severity == 2 ? 'Serious Thud' : ''}}
                    {{pothole.severity == 3 ? 'Flat Tire' : ''}}
                    {{pothole.severity == 4 ? 'Lost My Wheel' : ''}}
                    {{pothole.severity == 5 ? 'Car Swallower' : ''}}
                </li>
                <li>Reported: {{pothole.dateReported}}</li>
                <li>Inspected: {{pothole.dateInspected == null ? '*Awaiting Inspection*' : pothole.dateInspected}}</li>
                <li>Repaired: {{pothole.dateRepaired == null ? '*Awaiting Repair*' : pothole.dateRepaired}}</li>
            </ul>
        </div>
    </div>
</template>
    
<script>
import PotholeService from '../services/PotholeService';

export default {
    name: "pothole-list",
    data(){
        return {
            potholes:[],
        }
    },
    created(){
        PotholeService.listPotholes().then((response) => {
            this.potholes = response.data;
        })
    }
}
</script>

<style>
.list {
    display: flex;
    flex-direction: column;
    padding-top: 20px;
    font-family: Helvetica, sans-serif;
    font-size: 20px;
}
</style>