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
        <td>
            <template v-if="message">{{message}}</template>
            <template v-if="frontendData.currentUser.librarian">
                <div class="btn-group" role="group" aria-label="Actions"
                     v-if="exchange.reader !== null && exchange.startDate === null">
                    <button type="button" class="btn btn-outline-success btn-sm" @click="onConfirm">Confirm</button>
                    <button type="button" class="btn btn-outline-danger btn-sm" @click="onDecline">Decline</button>
                </div>

                <button type="button" class="btn btn-outline-secondary btn-sm" @click="onClose"
                        v-if="exchange.startDate !== null && exchange.endDate === null">Close
                </button>
            </template>

            <template v-if="frontendData.currentUser.reader && exchange.book.status === 'EXCHANGE'">
                <button type="button" class="btn btn-outline-success btn-sm" @click="onGetBook"
                        v-if="exchange.book.bookCondition === 'ON_EXCHANGE' && exchange.openExchangeDate !== null
                        && exchange.reader === null && exchange.owner.id !== frontendData.currentUser.id">
                    Get book
                </button>

                <button type="button" class="btn btn-outline-primary btn-sm" @click="onOpen"
                        v-if="exchange.book.bookCondition === 'RETURNED_TO_OWNER' && exchange.openExchangeDate === null
                        && exchange.owner.id === frontendData.currentUser.id">
                    Open
                </button>

                <button type="button" class="btn btn-outline-danger btn-sm" @click="onGetBookBack"
                        v-if="exchange.book.bookCondition === 'ON_EXCHANGE' && exchange.openExchangeDate !== null
                        && exchange.startDate === null && exchange.owner.id === frontendData.currentUser.id">Get
                    book back
                </button>
            </template>
        </td>
    </tr>
</template>


<script>
    export default {
        props: ['exchange', 'updateAll', 'frontendData'],
        data() {
            return {
                message: ''
            }
        },
        methods: {
            onConfirm() {
                this.$resource('/api/librarian/confirmExchange').update({
                        exchangeId: this.$props.exchange.id
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
                this.$resource('/api/librarian/declineExchange').update({
                        exchangeId: this.$props.exchange.id
                    }, {}
                ).then(result => {
                    this.$props.updateAll();
                }, result => {
                    result.json().then(response => {
                        this.message = response.error + ': ' + response.message;
                    })
                })
            },
            onClose() {
                this.$resource('/api/librarian/closeExchange').update({
                        exchangeId: this.$props.exchange.id
                    }, {}
                ).then(result => {
                    this.$props.updateAll();
                }, result => {
                    result.json().then(response => {
                        this.message = response.error + ': ' + response.message;
                    })
                })
            },
            onGetBook() {
                this.$resource('/api/reader/getBookByExchange').update({
                        exchangeId: this.$props.exchange.id
                    }, {}
                ).then(result => {
                    this.$props.updateAll();
                }, result => {
                    result.json().then(response => {
                        this.message = response.error + ': ' + response.message;
                    })
                })
            },
            onOpen() {
                this.$resource('/api/reader/openExchange').update({
                        exchangeId: this.$props.exchange.id
                    }, {}
                ).then(result => {
                    this.$props.updateAll();
                }, result => {
                    result.json().then(response => {
                        this.message = response.error + ': ' + response.message;
                    })
                })
            },
            onGetBookBack() {
                this.$resource('/api/reader/closeExchange').update({
                        exchangeId: this.$props.exchange.id
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