<template>
    <div>
        <navbar :frontendData="frontendData" :PagesEnum="PagesEnum"></navbar>

        <template v-if="frontendData.currentPage === PagesEnum.books">
            <book-list :books="books" :updateAll="updateAll" :frontendData="frontendData"
                       :borrowingsOfReader="borrowingsOfReader"/>
        </template>

        <template v-if="frontendData.currentPage === PagesEnum.borrowings">
            <borrowings-list :borrowings="frontendData.currentUser.librarian ? borrowings : borrowingsOfReader"
                             :updateAll="updateAll" :frontendData="frontendData"/>
        </template>

        <template v-if="frontendData.currentPage === PagesEnum.exchanges">
            <exchanges-list :exchanges="exchanges" :updateAll="updateAll" :frontendData="frontendData"/>
        </template>

        <template v-if="frontendData.currentPage === PagesEnum.orderings">
            <orderings-list :orderings="frontendData.currentUser.librarian ? orderings : ordersOfSupplier"
                            :updateAll="updateAll" :frontendData="frontendData"/>
        </template>

        <template v-if="frontendData.currentPage === PagesEnum.addBook">
            <book-add-form :updateAll="updateAll" :frontendData="frontendData" :PagesEnum="PagesEnum"/>
        </template>

        <template v-if="frontendData.currentPage === PagesEnum.users">
            <user-list :users="users" :getAllUsers="getAllUsers"/>
        </template>

        <template v-if="frontendData.currentPage === PagesEnum.addNewUser">
            <user-add-form :frontendData="frontendData" :PagesEnum="PagesEnum" :getAllUsers="getAllUsers"/>
        </template>
    </div>
</template>


<script>
    import BookList from 'components/books/BookList.vue'
    import Navbar from 'components/navbar/Navbar.vue'
    import UserList from "components/users/UserList.vue";
    import UserAddForm from "components/users/UserAddForm.vue";
    import BorrowingsList from "../components/libraryRecords/BorrowingsList.vue";
    import OrderingsList from "../components/libraryRecords/OrderingsList.vue";
    import ExchangesList from "../components/libraryRecords/ExchangesList.vue";
    import BookAddForm from "../components/books/BookAddForm.vue";

    export default {
        components: {
            BookAddForm,
            ExchangesList,
            OrderingsList,
            BorrowingsList,
            UserAddForm,
            UserList,
            BookList,
            Navbar
        },

        data() {
            return {
                frontendData: {
                    currentUser: {},
                    currentPage: 1,
                },
                books: [],
                borrowings: [],
                exchanges: [],
                orderings: [],
                users: [],
                PagesEnum: ({
                    "books": 1, "users": 2, "addNewUser": 3, "borrowings": 4, "exchanges": 5, "orderings": 6,
                    "addBook": 7
                })
            }
        },

        computed: {
            borrowingsOfReader() {
                return this.borrowings.filter(value => value.reader.id === this.frontendData.currentUser.id)
            },
            ordersOfSupplier() {
                return this.orderings.filter(value => value.supplier.id === this.frontendData.currentUser.id)
            },
        },

        created() {
            this.getFrontendData();
            this.getAllBooks();
            this.getAllUsers();
            this.getAllBorrowings();
            this.getAllExchanges();
            this.getAllOrderings();
        },

        methods: {
            getFrontendData() {
                return this.$resource('/api/user/current-data').get().then(result => {
                    result.json().then(data => {
                        this.frontendData = data
                    })
                })
            },
            getAllBooks() {
                return this.$resource('/api/book').get().then(result => {
                    result.json().then(data => {
                        this.books = data;
                        // this.frontendData.currentPage = this.PagesEnum.books
                    })
                })
            },
            getAllUsers() {
                return this.$resource('/api/user').get().then(result => {
                    result.json().then(data => {
                        this.users = data
                    })
                })
            },
            getAllBorrowings() {
                return this.$resource('/api/borrowings').get().then(result => {
                    result.json().then(data => {
                        this.borrowings = data
                    })
                })
            },
            getAllExchanges() {
                return this.$resource('/api/exchanges').get().then(result => {
                    result.json().then(data => {
                        this.exchanges = data
                    })
                })
            },
            getAllOrderings() {
                return this.$resource('/api/orderings').get().then(result => {
                    result.json().then(data => {
                        this.orderings = data
                    })
                })
            },
            updateAll() {
                this.getAllBooks();
                this.getAllUsers();
                this.getAllBorrowings();
                this.getAllExchanges();
                this.getAllOrderings();
            }
        }
    }
</script>


<style>
</style>