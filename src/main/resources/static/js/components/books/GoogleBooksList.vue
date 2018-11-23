<template>
    <div>
        <table class="table table-striped table-bordered table-hover">
            <thead class="header-bar text-white">
            <tr>
                <th scope="col">Title</th>
                <th scope="col">Author</th>
                <th scope="col">Publisher</th>
                <th scope="col">Publish date</th>
            </tr>
            </thead>
            <tbody>
            <google-books-row v-for="book in books" :key="makeId()" :book="book" :fillForm="fillForm"/>
            </tbody>
        </table>
    </div>
</template>


<script>
    import GoogleBooksRow from "./GoogleBooksRow.vue";

    export default {
        props: ['books', 'fillForm'],
        components: {
            GoogleBooksRow,
        },
        methods: {
            makeId() {
                let text = "";
                const possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                for (let i = 0; i < 5; i++)
                    text += possible.charAt(Math.floor(Math.random() * possible.length));
                return text;
            }
        },
    }
</script>


<style>

</style>