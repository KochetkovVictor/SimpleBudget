var ajaxUrl = 'ajax/purses/';
var datatableApi;

function updateTable() {

    $.get(ajaxUrl, updateTableByData);
}

$(function () {

    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": true,
        "info": true,
        "columns": [
            {
                "data": "description"
            },
            {
                "data": "amount"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                1,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.active) {
                $(row).css("text-decoration", "line-through");
                $(row).css("color", "grey");
            }
        },
        "initComplete": makeEditable
    });
});
