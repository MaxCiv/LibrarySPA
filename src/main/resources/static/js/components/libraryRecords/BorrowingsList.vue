<template>
    <div>
        <table class="table table-striped table-bordered table-hover">
            <thead class="header-bar text-white">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Book Id</th>
                <th scope="col">Book title</th>
                <th scope="col">Reader Id</th>
                <th scope="col">Reader login</th>
                <th scope="col">Start date</th>
                <th scope="col">End date</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <borrowings-row v-for="borrowing in borrowings" :key="borrowing.id" :borrowing="borrowing"/>
            </tbody>
        </table>
    </div>
</template>


<script>
    import BorrowingsRow from "./BorrowingsRow.vue";

    export default {
        props: ['borrowings', 'getAllBorrowings'],
        components: {
            BorrowingsRow
        },
    }
</script>


<style>

</style>