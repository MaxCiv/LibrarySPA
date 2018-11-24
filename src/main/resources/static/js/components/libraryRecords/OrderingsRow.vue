<template>
    <tr>
        <th scope="row">{{ordering.id}}</th>
        <td>{{ordering.book.id}}</td>
        <td>{{ordering.book.title}}</td>
        <td>{{ordering.supplier.id}}</td>
        <td>{{ordering.supplier.username}}</td>
        <td>{{ordering.startDate}}</td>
        <td>{{ordering.endDate}}</td>
        <td>
            <template v-if="message">{{message}}</template>
            <template v-if="frontendData.currentUser.supplier && ordering.endDate === null">
                <div class="btn-group" role="group" aria-label="Actions">
                    <button type="button" class="btn btn-outline-success btn-sm" @click="onFinish">Finish</button>
                    <button type="button" class="btn btn-outline-danger btn-sm" @click="onDecline">Decline</button>
                </div>
            </template>
        </td>
    </tr>
</template>


<script>
    export default {
        props: ['ordering', 'updateAll', 'frontendData'],
        data() {
            return {
                message: ''
            }
        },
        methods: {
            onFinish() {
                this.$resource('/api/supplier/finishOrder').update({
                        orderId: this.$props.ordering.id
                    }, {}
                ).then(result => {
                    this.$props.updateAll();
                }, result => {
                    result.json().then(response => {
                        this.message = response.error + ': ' + response.message;
                    })
                })
            },
            onDecline() {
                this.$resource('/api/supplier/denyOrder').update({
                        orderId: this.$props.ordering.id
                    }, {}
                ).then(result => {
                    this.$props.updateAll();
                }, result => {
                    result.json().then(response => {
                        this.message = response.error + ': ' + response.message;
                    })
                })
            },
        }
    }
</script>


<style>

</style>