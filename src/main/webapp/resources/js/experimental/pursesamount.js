var ajaxUrl = 'ajax/purses/totalAmount/';
var datatableApi;

function updateAmountTable() {
    debugger;
    $.get(ajaxUrl, updateAmountByData);
}
$(function () {
    debugger;
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "searching":false,
        "paging": false,
        "info": false,
        "columns": [
            {
                "orderable":false,
                "defaultContent": ""
            },
            {
                "orderable":false,
                "data": "value"
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).css("text-decoration", "line-through");
            }
        },
        "initComplete": makeEditable
    });
});