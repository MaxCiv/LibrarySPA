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
            <template v-if="message">{{message}}</template>
            <button v-if="frontendData.currentUser.librarian && book.bookCondition === 'NOT_AVAILABLE' && book.status === 'ORDER'" type="button"
                    class="btn btn-outline-warning btn-sm text-dark" @click="orderBook(book)">Order
            </button>
            <button v-if="canBorrow && frontendData.currentUser.reader && book.bookCondition === 'IN_LIBRARY' && book.status === 'LIBRARY'" type="button"
                    class="btn btn-outline-success btn-sm" @click="onBorrow">Borrow
            </button>
        </td>
    </tr>
</template>


<script>
    export default {
        props: ['book', 'orderBook', 'frontendData', 'borrowingsOfReader', 'updateAll'],
        data() {
            return {
                message: ''
            }
        },
        computed: {
            canBorrow() {
                return !this.$props.borrowingsOfReader.some(value => value.book.id === this.$props.book.id)
            }
        },
        methods: {
            onBorrow() {
                this.$resource('/api/reader/borrowBook').save({
                        bookId: this.$props.book.id
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