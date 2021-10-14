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
                <li v-else @click="logout">
                    <a href="/" class="button special">Logout</a>
                </li>
            </ul>
        </nav>
    </div>
</template>

<script>
module.exports = {
    data: function () {
        return {
            cookie: $cookies.get("accessToken")
        };
    },
    methods: {
        logout() {
            $cookies.remove("userId");
            $cookies.remove("accessToken");
            $cookies.remove("refreshToken");
        }
    }
}
</script>
