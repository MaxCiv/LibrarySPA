<template>
    <tr>
        <th scope="row">{{borrowing.id}}</th>
        <td>{{borrowing.book.id}}</td>
        <td>{{borrowing.book.title}}</td>
        <td>{{borrowing.reader.id}}</td>
        <td>{{borrowing.reader.username}}</td>
        <td>{{borrowing.startDate}}</td>
        <td>{{borrowing.endDate}}</td>
        <td>
            <template v-if="message">{{message}}</template>
            <template v-if="frontendData.currentUser.librarian">
                <div class="btn-group" role="group" aria-label="Actions"
                     v-if="borrowing.startDate === null">
                    <button type="button" class="btn btn-outline-success btn-sm" @click="onConfirm">Confirm</button>
                    <button type="button" class="btn btn-outline-danger btn-sm" @click="onDecline">Decline</button>
                </div>
                <button type="button" class="btn btn-outline-secondary btn-sm" @click="onClose"
                        v-if="borrowing.startDate !== null && borrowing.endDate === null">Close
                </button>
            </template>
        </td>
    </tr>
</template>


<script>
    export default {
        props: ['borrowing', 'updateAll', 'frontendData'],
        data() {
            return {
                message: ''
            }
        },
        methods: {
            onConfirm() {
                this.$resource('/api/librarian/confirmBorrowing').update({
                        borrowId: this.$props.borrowing.id
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
                this.$resource('/api/librarian/declineBorrowing').update({
                        borrowId: this.$props.borrowing.id
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
                this.$resource('/api/librarian/closeBorrowing').update({
                        borrowId: this.$props.borrowing.id
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