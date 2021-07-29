$(document).ready(() => {
    /* if (window.location.pathname.indexOf("/login") != -1) {
        $("#login-button").css("visibility", "hidden");
    } */

    $("#login-button").click(() => {
        window.location = "/login";
    });

    $("#logout-button").click(() => {
        $.ajax({
            method: "POST",
            url: "/logout",
            success: function() {
                location.reload();
            },
        });
    });
});