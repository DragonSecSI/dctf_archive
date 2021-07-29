$(document).ready(() => {
    /* var postsTimeout, xhr;
    $("#postsSearch").change(function() {
        if (postsTimeout) clearTimeout(postsTimeout);
        postsTimeout = setTimeout(function() {
            if (xhr) xhr.abort();
            xhr = $.ajax({
                type: "POST",
                url: "/admin/posts",
                data: {
                    query: $("#postsSearch").val(),
                    sql: "mysql"
                },
                success: function(res) {
                    console.log(res)
                },
                dataType: "json",
            });
        }, 600);
    }); */

    $('.has-clear input[type="search"]').on('input propertychange', function() {
        var $this = $(this);
        var visible = Boolean($this.val());
        $this.siblings('.form-control-clear').toggleClass('hidden', !visible);
    }).trigger('propertychange');

    $('.form-control-clear').click(function() {
        $(this).siblings('input[type="search"]').val('')
            .trigger('propertychange').focus();
        $(this).siblings('button[type="search"]').click()
    });
});
$(function() {
    $('[data-toggle="popover"]').popover()
})