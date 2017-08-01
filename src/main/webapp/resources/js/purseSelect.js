var ajaxUrl = 'ajax/purses/';

$(document).ready(function(){
    $.get(ajaxUrl, function (data) {
        $.each(data, function(key, value){
            $("select[name='fromPurse']").append(
                $("<option value ="+ value.id+">" + value.description+"</option>"));
        });

    });
});

$(document).ready(function(){
    $.get(ajaxUrl, function (data) {
        $.each(data, function(key, value){
            $("select[name='toPurse']").append(
                $("<option value = "+ value.id+" >" + value.description+"</option>"));
        });

    });
});