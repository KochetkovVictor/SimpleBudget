var ajaxUrl = 'ajax/purses/';
var totalAmountUrl = 'ajax/purses/totalAmount/';
var datatableApi;
var totalAmountApi;

function updateTable() {
    debugger;
    $.get(ajaxUrl, updateTableByData);
    $.ajax({
        dataType: "json",
        url: totalAmountUrl,
        data:data
    } );
}
$(function () {
    totalAmountApi = $('#totalamount').DataTable({
        "ajax": {
            "url": totalAmountUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "columns": [
            {
                "orderable":false,
                "data": ""
            },
            {
                "orderable":false,
                "data": ""
            }
        ]
        /*"createdRow": function (row, data, dataIndex) {
            if (!data.active) {
                $(row).css("text-decoration", "line-through");
            }
        },
        "initComplete": makeEditable*/
    });
});
$(function () {

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
