var ajaxUrl='ajax/users';
$('#userDOB').datetimepicker({
    timepicker: false,
    format: 'Y-m-d',
    lang: 'ru',
    formatDate: 'Y-m-d'
});
function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            updateTable();
            successNoty('Saved');
        }
    });
}
