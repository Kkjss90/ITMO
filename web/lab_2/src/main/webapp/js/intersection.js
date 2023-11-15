function send_intersection_rq(x, y, r) {
    $.ajax({
        type: "POST",
        url: ctx + "/ControllerServlet",
        async: false,
        data: {"X": x, "Y": y, "R": r},
        success: function (data) {
            window.location.replace('./index.jsp');
        },
        error: function (xhr, textStatus, err) {
            showError(document.getElementById('buttons-table'), "readyState: " + xhr.readyState + "\n" +
                "responseText: " + xhr.responseText + "\n" +
                "status: " + xhr.status + "\n" +
                "text status: " + textStatus + "\n" +
                "error: " + err);
        }
    });
}

