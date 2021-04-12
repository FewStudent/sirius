function ajax_common(url, token, success) {
    $.ajax({
        url: url,
        type: 'post',
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        headers: {
            'token': token
        },
        success: function (data) {
            success(data);
        }
    });
}

function ajax_data(url, token, data, success) {
    $.ajax({
        url: url,
        type: 'post',
        dataType: "json",
        data: data,
        contentType: "application/json; charset=utf-8",
        headers: {
            'token': token
        },
        success: function (result) {
            success(result);
        }
    });
}

function ajax_common_error(url, token, success, error) {
    $.ajax({
        url: url,
        type: 'post',
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        headers: {
            'token': token
        },
        success: function (data) {
            success(data);
        }, error: function (msg) {
            error(msg);
        }
    });
}