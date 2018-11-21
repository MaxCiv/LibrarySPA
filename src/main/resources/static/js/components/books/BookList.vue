<template>
    <div>
        <table class="table table-striped table-bordered table-hover">
            <thead class="header-bar text-white">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Title</th>
                <th scope="col">Author</th>
                <th scope="col">Publisher</th>
                <th scope="col">Year</th>
                <th scope="col">Status</th>
                <th scope="col">Book condition</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <book-row v-for="book in books" :key="book.id" :book="book"/>
            </tbody>
        </table>
    </div>
</template>


<script>
    import BookRow from 'components/books/BookRow.vue'

    export default {
        props: ['books'],
        components: {
            BookRow
        },
    }
</script>


<style>

</style>