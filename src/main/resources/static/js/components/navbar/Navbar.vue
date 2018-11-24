<template>
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark header-bar px-5">
        <a class="navbar-brand" href="/">LibraryApp</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item" :class="{active : frontendData.currentPage === PagesEnum.books}" @click="goTo(PagesEnum.books)">
                    <a class="nav-link" href="#">Books</a>
                </li>

                <template v-if="frontendData.currentUser.librarian">
                    <li class="nav-item" :class="{active : frontendData.currentPage === PagesEnum.borrowings}" @click="goTo(PagesEnum.borrowings)">
                        <a class="nav-link" href="#">Borrowings</a>
                    </li>

                    <li class="nav-item" :class="{active : frontendData.currentPage === PagesEnum.exchanges}" @click="goTo(PagesEnum.exchanges)">
                        <a class="nav-link" href="#">Exchanges</a>
                    </li>

                    <li class="nav-item" :class="{active : frontendData.currentPage === PagesEnum.orderings}" @click="goTo(PagesEnum.orderings)">
                        <a class="nav-link" href="#">Orderings</a>
                    </li>

                    <li class="nav-item" :class="{active : frontendData.currentPage === PagesEnum.users}" @click="goTo(PagesEnum.users)">
                        <a class="nav-link" href="#">Users</a>
                    </li>

                    <li class="nav-item" :class="{active : frontendData.currentPage === PagesEnum.addBook}" @click="goTo(PagesEnum.addBook)">
                        <a class="nav-link" href="#">Add new book</a>
                    </li>

                    <li class="nav-item" :class="{active : frontendData.currentPage === PagesEnum.addNewUser}" @click="goTo(PagesEnum.addNewUser)">
                        <a class="nav-link" href="#">Add new user</a>
                    </li>
                </template>

                <template v-if="frontendData.currentUser.reader">
                    <li class="nav-item" :class="{active : frontendData.currentPage === PagesEnum.borrowings}" @click="goTo(PagesEnum.borrowings)">
                        <a class="nav-link" href="#">My borrowings</a>
                    </li>

                    <li class="nav-item" :class="{active : frontendData.currentPage === PagesEnum.exchanges}" @click="goTo(PagesEnum.exchanges)">
                        <a class="nav-link" href="#">Exchanges</a>
                    </li>
                </template>

                <template v-if="frontendData.currentUser.supplier">
                    <li class="nav-item" :class="{active : frontendData.currentPage === PagesEnum.orderings}" @click="goTo(PagesEnum.orderings)">
                        <a class="nav-link" href="#">My orderings</a>
                    </li>
                </template>

            </ul>

            <div class="navbar-text px-3" :title="frontendData.currentUser.username">
                {{frontendData.currentUser.name}}
            </div>

            <form class="form-inline my-2 my-lg-0" action="/logout" method="post">
                <button class="btn btn-outline-primary my-2 my-sm-0 text-white" type="submit">Sign out</button>
            </form>
        </div>
    </nav>
</template>


<script>
    export default {
        props: ['frontendData', 'PagesEnum'],
        components: {},
        data() {
            return {}
        },
        created() {
        },
        methods: {
            goTo(page) {
                this.$props.frontendData.currentPage = page;
            }
        }
    }
</script>


<style>

</style>