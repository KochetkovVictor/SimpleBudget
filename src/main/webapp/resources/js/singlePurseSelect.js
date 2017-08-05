var ajaxPurseUrl = 'ajax/purses/filtered';

$(document).ready(function(){
    $.get(ajaxPurseUrl, function (data) {
        $.each(data, function(key, value){
            $("select[name='editedPurse']").append(
                $("<option value = "+ value.id+" >" + value.description+"</option>"));
        });
    });
});