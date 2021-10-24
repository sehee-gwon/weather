<template>
    <div id="header">
        <h1><a href="/">Daily Weather</a></h1>
        <nav id="nav">
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/weather.html">Weather</a></li>
        <!--        <li><a href="/board/list.html">Board</a></li>-->
                <li><a href="/elements.html">element</a></li>
                <li v-if="cookie == null">
                    <a href="/login.html" class="button special">Login</a>
                </li>
                <li v-else>
                    <a href="javascript:void(0);" class="button special" @click="logout">Logout</a>
                </li>
            </ul>
        </nav>
    </div>
</template>

<script>
module.exports = {
    data: function() {
        return {
            cookie: $cookies.get("userId")
        }
    },
    methods: {
        logout: function() {
            axios.get("/logout", {
                headers: {
                    Authorization: 'Bearer ' + this.cookie
                }
            })
            .then(function(response) {
                location.href = "/";
            })
            .catch(function(error) {
                this.handlerException(error.response);
            });
        }
    }
}
</script>
