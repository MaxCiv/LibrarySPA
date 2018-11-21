<template>
    <tr>
        <th scope="row">{{ordering.id}}</th>
        <td>{{ordering.book.id}}</td>
        <td>{{ordering.book.title}}</td>
        <td>{{ordering.supplier.id}}</td>
        <td>{{ordering.supplier.username}}</td>
        <td>{{ordering.startDate}}</td>
        <td>{{ordering.endDate}}</td>
        <td></td>
    </tr>
</template>


<script>
    export default {
        props: ['ordering']
    }
</script>


<style>

</style>