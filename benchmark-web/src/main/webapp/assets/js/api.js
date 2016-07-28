$(document).ready(function () {
    var putData = {};
    $.ajax({
        type: "POST",
        url: "http://cp01-tieba-data-1017.cp01.baidu.com:8020/benchmark/echo/echo.do",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(putData),
        success: function (msg) {
            if (msg['status'] == 200) {
                console.log("success");
            } else {
                console.log("failed");
            }
        }
    });
})