<template>
    <div>
        <div class="text-center mx-auto">
            <form class="form-signin" @submit="onSubmit">
                <h1 class="h3 mb-3 font-weight-normal">Add new book</h1>

                <label for="inputTitle" class="sr-only">Title</label>
                <input type="text" id="inputTitle" class="form-control input-top" v-model="form.title"
                       placeholder="Title" @input="getGoogleBooksByTitle(form.title)" required autofocus>

                <label for="inputAuthor" class="sr-only">Author</label>
                <input type="text" id="inputAuthor" class="form-control input-middle" v-model="form.author"
                       placeholder="Author" @input="getGoogleBooksByAuthor(form.author)" required>

                <label for="inputPublisher" class="sr-only">Publisher</label>
                <input type="text" id="inputPublisher" class="form-control input-middle" v-model="form.publisher"
                       placeholder="Publisher" required>

                <label for="inputPublishDate" class="sr-only">Publish date</label>
                <input type="text" id="inputPublishDate" class="form-control input-bottom" v-model="form.publishYear"
                       placeholder="Publish date" required>

                <label for="inputOwner" class="sr-only">Owner Id</label>
                <input type="number" id="inputOwner" class="form-control input-bottom" v-model="form.ownerId"
                       placeholder="Owner Id" v-show="form.status === '1'">

                <div class="btn-group btn-group-toggle mx-auto pb-2" data-toggle="buttons">
                    <label class="btn btn-light" :class="{active : form.status === '0'}">
                        <input type="radio" v-model="form.status" value="0"> Library book
                    </label>
                    <label class="btn btn-light" :class="{active : form.status === '1'}">
                        <input type="radio" v-model="form.status" value="1"> Exchanging book
                    </label>
                    <label class="btn btn-light" :class="{active : form.status === '2'}">
                        <input type="radio" v-model="form.status" value="2"> Ordered book
                    </label>
                </div>

                <p v-show="message">{{message}}</p>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
            </form>
        </div>

        <google-books-list :books="googleBooks" :fillForm="fillForm"></google-books-list>
    </div>
</template>


<script>
    import GoogleBooksList from "./GoogleBooksList.vue";

    export default {
        components: {GoogleBooksList},

        props: ['updateAll', 'frontendData', 'PagesEnum'],

        data() {
            return {
                googleBooks: [],
                message: '',
                form: {
                    title: '',
                    author: '',
                    publisher: '',
                    publishYear: '',
                    status: '0',
                    ownerId: '',
                },
            }
        },

        created() {
            this.getGoogleBooksByTitle("java")
        },

        methods: {
            onSubmit() {
                this.$resource('/api/librarian/addBook').save({
                        ownerId: this.form.ownerId,
                    }, {
                        title: this.form.title,
                        author: this.form.author,
                        publisher: this.form.publisher,
                        publishYear: this.form.publishYear,
                        status: this.form.status,
                    }
                ).then(result => {
                    this.$props.updateAll();
                    this.$props.frontendData.currentPage = this.$props.PagesEnum.books;
                }, result => {
                    result.json().then(response => {
                        this.message = response.error + ': ' + response.message;
                    })
                })
            },
            getGoogleBooksByTitle(title) {
                return this.$resource('/api/book/google/title').get({title: title}).then(result => {
                    result.json().then(data => {
                        this.googleBooks = data
                    })
                })
            },
            getGoogleBooksByAuthor(author) {
                return this.$resource('/api/book/google/author').get({author: author}).then(result => {
                    result.json().then(data => {
                        this.googleBooks = data
                    })
                })
            },
            fillForm(book) {
                this.form.title = book.title;
                this.form.author = book.author;
                this.form.publisher = book.publisher;
                this.form.publishYear = book.publishYear;
            },
        }
    }
</script>


<style>
    .form-signin {
        width: 100%;
        max-width: 550px;
        padding: 15px;
        margin: auto;
    }
</style>