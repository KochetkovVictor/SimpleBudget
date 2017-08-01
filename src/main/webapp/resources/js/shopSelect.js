var ajaxShopUrl = 'ajax/shops/';
//var ajaxPurseUrl = 'ajax/purse/filtered';

$(document).ready(function(){
    $.get(ajaxShopUrl, function (data) {
        $.each(data, function(key, value){
            $("select[name='editedShop']").append(
                $("<option value ="+ value.id+">" + value.name+"</option>"));
        });
    });
});

$(document).ready(function(){
    $.get(ajaxPurseUrl, function (data) {
        $.each(data, function(key, value){
            $("select[name='editedPurse']").append(
                $("<option value = "+ value.id+" >" + value.description+"</option>"));
        });
    });
});