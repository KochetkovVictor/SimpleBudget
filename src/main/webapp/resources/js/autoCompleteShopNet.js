var ajaxUrlShopNet='ajax/shopNet/';
var source=[
    {"value":"201","label":"Пятерочка"},
    {"value":"202","label":"Ашан"},
    {"value":"203","label":"Виктория"},
    {"value":"204","label":"Дикси"}
    ];


$('#shopNet').autocomplete({
    source: source, // Страница для обработки запросов автозаполнения
    dataType:'json',
    minChars: 1, // Минимальная длина запроса для срабатывания автозаполнения
    delimiter: /([,;])\s*/, // Разделитель для нескольких запросов, символ или регулярное выражение
    maxHeight: 400, // Максимальная высота списка подсказок, в пикселях
    width: 300, // Ширина списка
    zIndex: 9999, // z-index списка
    delay: 1000,
    deferRequestBy: 300

});