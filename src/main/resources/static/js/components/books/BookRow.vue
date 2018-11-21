<template>
    <tr>
        <th scope="row">{{book.id}}</th>
        <td>{{book.title}}</td>
        <td>{{book.author}}</td>
        <td>{{book.publisher}}</td>
        <td>{{book.publishYear}}</td>
        <td>{{book.status}}</td>
        <td>{{book.bookCondition}}</td>
        <td></td>
    </tr>
</template>


<script>
    export default {
        props: ['book']
    }
</script>


<style>

</style>