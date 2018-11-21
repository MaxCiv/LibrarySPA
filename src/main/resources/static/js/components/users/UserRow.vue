<template>
    <tr>
        <th scope="row">{{user.id}}</th>
        <td>{{user.username}}</td>
        <td>{{user.name}}</td>
        <td>{{user.roles.join(", ")}}</td>
        <td>
            <button type="button" class="btn btn-outline-info btn-sm" @click="startEdit(user)">Edit</button>
        </td>
    </tr>
</template>


<script>
    export default {
        props: ['user', 'startEdit'],
        data() {
            return {
            }
        },
        methods: {
        }
    }
</script>


<style>

</style>