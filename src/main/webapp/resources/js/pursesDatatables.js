var ajaxUrl = 'ajax/purses/';
var datatableApi;

function updateTable() {
    debugger;
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    debugger;
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "scrollY":        "200px",
        "scrollCollapse": true,
        "paging": true,
        "info": false,
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
                0,
                "asc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.active) {
                $(row).css("text-decoration", "line-through");
            }
        },
        "initComplete": makeEditable
    });

});
