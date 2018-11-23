<template>
    <tr>
        <th scope="row">{{exchange.id}}</th>
        <td>{{exchange.book.id}}</td>
        <td>{{exchange.book.title}}</td>
        <td>{{exchange.owner.id}}</td>
        <td>{{exchange.owner.username}}</td>
        <td>{{exchange.openExchangeDate}}</td>
        <td>{{exchange.reader ? exchange.reader.id : ''}}</td>
        <td>{{exchange.reader ? exchange.reader.username : ''}}</td>
        <td>{{exchange.startDate}}</td>
        <td>{{exchange.endDate}}</td>
        <td></td>
    </tr>
</template>


<script>
    export default {
        props: ['exchange']
    }
</script>


<style>

</style>