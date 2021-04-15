<template>
    <div class="side-container">
        <div class="add-pothole">
            <user-menu id="user-menu" v-show=" userRole == 'ROLE_USER' " />
            <guest-menu id="guest-menu" v-show=" userRole == 'ROLE_GUEST' " />
            <employee-menu id="emp-menu" v-show=" userRole == 'ROLE_EMPLOYEE' " />
        </div>
    </div>
</template>

<script>
import UserMenu from '@/components/MenuComponents/UserMenu';
import GuestMenu from '@/components/MenuComponents/GuestMenu';
import EmployeeMenu from '@/components/MenuComponents/EmployeeMenu';

export default {
    name: "SideMenu",
    components: {
        UserMenu,
        GuestMenu,
        EmployeeMenu
    },
    data () {
        return {
            userRole: this.$store.state.user.authorities[0].name
        }
    }
}
</script>


<style>
.side-container {
    display: grid;
    padding-left: 10px;
    grid-template-areas: 
        "topline"
        "add-pothole"
        "add-pothole"
        "add-pothole";
    overflow: scroll;
    z-index: 1;
    margin-top:120px;
    height:85vh;
    
}
.add-pothole {
    grid-area: add-pothole;
}
.topline {
    grid-area: topline;
    display: flex;
    justify-content: center;
    align-items: center;
}


</style>