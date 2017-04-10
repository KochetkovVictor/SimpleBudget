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
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "description"
            },
            {
                "data": "amount"
            },
            {
                "data": "active",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="enable($(this),' + row.id + ');"/>';
                    }
                    return data;
                }
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