<template>
    <div>
        <user-edit-form v-if="editing" :user="userForEdit" :stopEdit="stopEdit" :getAllUsers="getAllUsers"></user-edit-form>
        <table class="table table-striped table-bordered table-hover">
            <thead class="header-bar text-white">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Login</th>
                <th scope="col">Name</th>
                <th scope="col">Roles</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <user-row v-for="user in users" :key="user.id" :user="user" :startEdit="startEdit"/>
            </tbody>
        </table>
    </div>
</template>


<script>
    import UserRow from "components/users/UserRow.vue";
    import UserEditForm from "components/users/UserEditForm.vue";

    export default {
        props: ['users', 'getAllUsers'],
        components: {
            UserRow,
            UserEditForm
        },
        data() {
            return {
                editing: false,
                userForEdit: null
            }
        },
        methods: {
            startEdit(user) {
                this.userForEdit = user;
                this.editing = true;
            },
            stopEdit() {
                this.editing = false;
            }
        }
    }
</script>


<style>

</style>