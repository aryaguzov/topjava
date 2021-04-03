const mealAjaxUrl = "profile/meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealAjaxUrl
};

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        })
    );
});

let filter = "";

function getFilter() {
    $.ajax({
        url: ctx.ajaxUrl + "filter/?" + $('#filterForm'),
        type: "GET"
    }).done(function (data) {
        clearAndAddData(data)
        filter = "filter/?" + $('#filterForm');
    });
}

function updateTable() {
    $.get(ctx.ajaxUrl + filter, function (data) {
        clearAndAddData(data);
    });
}