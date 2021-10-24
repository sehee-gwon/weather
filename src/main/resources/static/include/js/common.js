const pages = {
    LOGIN_PAGE: "/login.html"
}

function handlerException(response) {
    if (response.status === 401) {
        location.href = pages.LOGIN_PAGE;
    } else {
        console.error(response);
        alert(response.data.error + ": " + response.data.message);
    }
}