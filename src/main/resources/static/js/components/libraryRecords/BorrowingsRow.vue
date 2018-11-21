<template>
    <tr>
        <th scope="row">{{borrowing.id}}</th>
        <td>{{borrowing.book.id}}</td>
        <td>{{borrowing.book.title}}</td>
        <td>{{borrowing.reader.id}}</td>
        <td>{{borrowing.reader.username}}</td>
        <td>{{borrowing.startDate}}</td>
        <td>{{borrowing.endDate}}</td>
        <td></td>
    </tr>
</template>


<script>
    export default {
        props: ['borrowing']
    }
</script>


<style>

</style>