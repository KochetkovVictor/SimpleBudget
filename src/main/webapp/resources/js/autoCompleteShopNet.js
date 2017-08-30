var ajaxUrlShopNet = 'ajax/shopNet';

$('#shopNet').autocomplete({
    serviceUrl: ajaxUrlShopNet+'/filter',
    transformResult: function (response) {
        response=JSON.parse(response);
        return {
            suggestions: $.map(response, function (dataItem) {
                return {value: dataItem.name, data: dataItem.id};
            })
        };
    },
    onSelect: function (suggestion) {
        alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
    }
});

