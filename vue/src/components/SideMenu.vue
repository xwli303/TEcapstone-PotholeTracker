<template>
    <div class="side-container">
        <div class="topline">HOME</div>
        <div class="add-pothole">
            <user-menu v-show=" userRole == 'ROLE_USER' " />
            <guest-menu v-show=" userRole == 'ROLE_GUEST' " />
            <employee-menu v-show=" userRole == 'ROLE_EMPLOYEE' " />
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
    grid-template-areas: 
        "topline"
        "add-pothole"
        "add-pothole"
        "add-pothole";
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