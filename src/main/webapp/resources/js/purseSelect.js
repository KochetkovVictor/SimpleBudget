var ajaxPurseUrl = 'ajax/purses/filtered';

$(document).ready(function(){
    $.get(ajaxPurseUrl, function (data) {
        $.each(data, function(key, value){
            $("select[name='fromPurse']").append(
                $("<option value ="+ value.id+">" + value.description+"</option>"));
        });
    });
});

$(document).ready(function(){
    $.get(ajaxPurseUrl, function (data) {
        $.each(data, function(key, value){
            $("select[name='toPurse']").append(
                $("<option value = "+ value.id+" >" + value.description+"</option>"));
        });
    });
});