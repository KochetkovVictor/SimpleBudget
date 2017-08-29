var ajaxUrlShopNet='ajax/shopNet';


$('#shopNet').autocomplete({
    lookup: function (query, done) {
        // Do Ajax call or lookup locally, when done,
        // call the callback and pass your results:
        var result = $.get(ajaxUrlShopNet);
        done(result);
    },
    onSelect: function (suggestion) {
        alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
    }
});
/*
$('#shopNet').autocomplete({
    serviceUrl:ajaxUrlShopNet+'/filter',
    onSelect: function (suggestion) {
        alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
    }
});*/
