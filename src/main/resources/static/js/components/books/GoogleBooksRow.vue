<template>
    <tr @click="fillForm(book)">
        <td>{{book.title}}</td>
        <td>{{book.author}}</td>
        <td>{{book.publisher}}</td>
        <td>{{book.publishYear}}</td>
    </tr>
</template>


<script>
    export default {
        props: ['book', 'fillForm']
    }
</script>


<style>

</style>