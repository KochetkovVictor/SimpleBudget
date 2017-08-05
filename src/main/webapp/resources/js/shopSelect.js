var ajaxShopUrl = 'ajax/shops/';

$(document).ready(function(){
    $.get(ajaxShopUrl, function (data) {
        $.each(data, function(key, value){
            $("select[name='editedShop']").append(
                $("<option value ="+ value.id+">" + value.name+"</option>"));
        });
    });
});

