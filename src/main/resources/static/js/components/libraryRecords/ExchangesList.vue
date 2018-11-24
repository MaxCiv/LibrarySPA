<template>
    <div>
        <table class="table table-striped table-bordered table-hover">
            <thead class="header-bar text-white">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Book Id</th>
                <th scope="col">Book title</th>
                <th scope="col">Owner Id</th>
                <th scope="col">Owner login</th>
                <th scope="col">Open date</th>
                <th scope="col">Reader Id</th>
                <th scope="col">Reader login</th>
                <th scope="col">Start date</th>
                <th scope="col">End date</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <exchanges-row v-for="exchange in exchanges" :key="exchange.id" :exchange="exchange" :updateAll="updateAll"
                           :frontendData="frontendData"/>
            </tbody>
        </table>
    </div>
</template>


<script>
    import ExchangesRow from "./ExchangesRow.vue";

    export default {
        props: ['exchanges', 'updateAll', 'frontendData'],
        components: {
            ExchangesRow,
        },
    }
</script>


<style>

</style>