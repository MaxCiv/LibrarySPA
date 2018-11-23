<template>
    <tr>
        <th scope="row">{{book.id}}</th>
        <td>{{book.title}}</td>
        <td>{{book.author}}</td>
        <td>{{book.publisher}}</td>
        <td>{{book.publishYear}}</td>
        <td>{{book.status}}</td>
        <td>{{book.bookCondition}}</td>
        <td>
            <button v-show="book.bookCondition === 'NOT_AVAILABLE' && book.status === 'ORDER'" type="button"
                    class="btn btn-outline-warning btn-sm text-dark" @click="orderBook(book)">Order
            </button>
        </td>
    </tr>
</template>


<script>
    export default {
        props: ['book', 'orderBook']
    }
</script>


<style>

</style>