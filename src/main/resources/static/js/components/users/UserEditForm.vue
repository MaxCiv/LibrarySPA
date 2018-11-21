<template>
    <div class="text-center mx-auto">
        <form class="form-signin" @submit="onSubmit">
            <h1 class="h3 mb-3 font-weight-normal">Editing ID: {{user.id}}</h1>
            <h1 class="h3 mb-3 font-weight-normal">{{user.username}}</h1>

            <label for="inputName" class="sr-only">Name</label>
            <input type="text" id="inputName" class="form-control input-top" v-model="form.name" placeholder="Name"
                   required autofocus>

            <label for="inputUsername" class="sr-only">Username</label>
            <input type="text" id="inputUsername" class="form-control input-middle" v-model="form.username"
                   placeholder="Login"
                   required>

            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control input-middle" v-model="form.password"
                   placeholder="Password" required>

            <label for="inputPassword2" class="sr-only">Repeat password</label>
            <input type="password" id="inputPassword2" class="form-control input-bottom" v-model="form.password2"
                   placeholder="Repeat password" required>

            <div class="btn-group btn-group-toggle mx-auto pb-2" data-toggle="buttons">
                <label class="btn btn-light" :class="{active : form.role === 'Reader'}">
                    <input type="radio" v-model="form.role" value="Reader"> Reader
                </label>
                <label class="btn btn-light" :class="{active : form.role === 'Supplier'}">
                    <input type="radio" v-model="form.role" value="Supplier"> Supplier
                </label>
                <label class="btn btn-light" :class="{active : form.role === 'Librarian'}">
                    <input type="radio" v-model="form.role" value="Librarian"> Librarian
                </label>
            </div>

            <p v-show="message">{{message}}</p>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
            <button class="btn btn-lg btn-outline-primary btn-block" type="button" @click="stopEdit">Cancel</button>
        </form>
    </div>
</template>


<script>
    export default {
        props: ['stopEdit', 'user', 'getAllUsers'],
        data() {
            return {
                message: '',
                form: {
                    name: "",
                    username: "",
                    password: "",
                    password2: "",
                    role: "Reader"
                }
            }
        },
        created() {
            this.form.name = this.$props.user.name;
            this.form.username = this.$props.user.username;
            if (this.$props.user.librarian) {
                this.form.role = 'Librarian';
            } else if (this.$props.user.supplier) {
                this.form.role = 'Supplier';
            }
        },
        methods: {
            onSubmit() {
                if (this.form.password === this.form.password2) {
                    this.$resource('/api/user{/id}/edit').update({
                            id: this.$props.user.id,
                            role: this.form.role,
                            name: this.form.name,
                            username: this.form.username,
                            password: this.form.password
                        }, {}
                    ).then(result => {
                        this.$props.getAllUsers();
                        this.$props.stopEdit();
                    }, result => {
                        result.json().then(response => {
                            this.message = response.error + ': ' + response.message;
                        })
                    })
                } else this.message = "Passwords are different"
            }
        }
    }
</script>


<style>

</style>