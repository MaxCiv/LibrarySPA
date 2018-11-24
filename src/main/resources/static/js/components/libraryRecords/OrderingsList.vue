<template>
    <div>
        <table class="table table-striped table-bordered table-hover">
            <thead class="header-bar text-white">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Book Id</th>
                <th scope="col">Book title</th>
                <th scope="col">Supplier Id</th>
                <th scope="col">Supplier login</th>
                <th scope="col">Start date</th>
                <th scope="col">End date</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <orderings-row v-for="ordering in orderings" :key="ordering.id" :ordering="ordering" :updateAll="updateAll"
                           :frontendData="frontendData"/>
            </tbody>
        </table>
    </div>
</template>


<script>
    import OrderingsRow from "./OrderingsRow.vue";

    export default {
        props: ['orderings', 'updateAll', 'frontendData'],
        components: {
            OrderingsRow,
        },
    }
</script>


<style>

</style>