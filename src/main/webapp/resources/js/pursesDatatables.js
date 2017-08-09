var ajaxUrl = 'ajax/purses/';
var datatableApi;

$.ajax({
    url: ajaxUrl,
    type: 'POST',
    success: updateTableByData
});

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
        "initComplete": makeEditable,
        "footerCallback": function ( row, data, start, end, display ) {
            var api = this.api();

            // Remove the formatting to get integer data for summation
            var intVal = function ( i ) {
                return typeof i === 'string' ?
                    i.replace(/[\$,]/g, '')*1 :
                    typeof i === 'number' ?
                        i : 0;
            };

            // Total over all pages

            var total = api
                .column(1)
                .data()
                .reduce(function (a, b) {
                    return intVal(a) + intVal(b);
                }, 0);

            // Total over this page
            var pageTotal = api
                .column( 1, { page: 'current'} )
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                }, 0 );

            // Update footer
            $( api.column( 1 ).footer() ).html(
                pageTotal+' rub' +' ( '+ total + ' rub'+' total)'
            );
        }
    });
});
