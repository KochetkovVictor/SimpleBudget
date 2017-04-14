var ajaxUrl = 'ajax/incomes/';
var datatableApi;

function updateTable() {
    /*$.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: updateTableByData
    });
    return false;*/
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
        "paging": true,
        "info": false,
        "columns": [
            {
                "data": "description"
            },
            {
                "data": "incomeDate",
                "render": function (date, type, row) {
                    /*if (type == 'display') {
                        return date.replace('T', ' ').substr(0, 16);
                    }*/
                    return date;
                }
            },

            {
                "data": "value"
            },
            {
                "data":"purse.description"
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderEditBtn
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderDeleteBtn

            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            $(row).addClass(data.exceed ? 'exceeded' : 'normal');
        },
        "initComplete": function () {
            $('#filter').submit(function () {
                updateTable();
                return false;
            });

            var startDate = $('#startDate');
            var endDate = $('#endDate');
            
            startDate.datetimepicker({
                timepicker: false,
                format: 'Y-m-d',
                lang: 'ru',
                formatDate: 'Y-m-d',
                onShow: function (ct) {
                    this.setOptions({
                        maxDate: endDate.val() ? endDate.val() : false
                    })
                }
            });
            endDate.datetimepicker({
                timepicker: false,
                format: 'Y-m-d',
                lang: 'ru',
                formatDate: 'Y-m-d',
                onShow: function (ct) {
                    this.setOptions({
                        minDate: startDate.val() ? startDate.val() : false
                    })
                }
            });

            $('.time-picker').datetimepicker({
                datepicker: false,
                format: 'H:i',
                lang: 'ru'
            });

            $('#dateTime').datetimepicker({
                format: 'Y-m-d H:i',
                lang: 'ru'
            });

            makeEditable();
        }
    });
});
