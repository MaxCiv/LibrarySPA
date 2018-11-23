<template>
    <div>
        <form v-if="bookForOrder" @submit="onSubmit">
            <div class="form-row mb-3">
                <div class="col-9">
                    <h4>Order book (ID: {{bookForOrder.id}}) {{bookForOrder.title}}</h4>
                </div>
                <div class="col">
                    <input type="number" class="form-control" v-model="supplierId" placeholder="Supplier Id" required>
                </div>
                <div class="col">
                    <button class="btn btn-primary btn-block" type="submit">Order</button>
                </div>
                <div class="col">
                    <button class="btn btn-outline-primary btn-block" type="button" @click="cancel">Cancel</button>
                </div>
            </div>
            <div class="row" v-show="message">
                <div class="col">
                    <p>{{message}}</p>
                </div>
            </div>
        </form>

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
            <book-row v-for="book in books" :key="book.id" :book="book" :orderBook="orderBook"/>
            </tbody>
        </table>
    </div>
</template>


<script>
    import BookRow from 'components/books/BookRow.vue'

    export default {
        props: ['books', 'updateAll'],
        components: {
            BookRow
        },

        data() {
            return {
                bookForOrder: null,
                supplierId: '',
                message: ''
            }
        },

        methods: {
            orderBook(book) {
                this.bookForOrder = book
            },
            onSubmit() {
                this.$resource('/api/librarian/orderBook').update({
                    book: this.bookForOrder.id,
                    supplierId: this.supplierId,
                    }, {}
                ).then(result => {
                    this.$props.updateAll();
                    this.cancel();
                }, result => {
                    result.json().then(response => {
                        this.message = response.error + ': ' + response.message;
                    })
                })
            },
            cancel() {
                this.bookForOrder = null
            }
        }
    }
</script>


<style>

</style>