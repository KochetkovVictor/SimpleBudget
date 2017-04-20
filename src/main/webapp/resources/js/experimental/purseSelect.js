var url='ajax/purses/';


var newOptions = function func(){
    var name = "World" ;
    alert("Hi") ;
    $.getJSON(url,{name:name},function(data){
        alert("It works");
        alert(data);
    });
};
//способ 1
$.each(newOptions, function(key, value) {
    $('#select1').append($("", {
        value: key,
        text: value
    }));
});
$.each(newOptions, function(key, value) {
    $('#select2').append($("", {
        value: key,
        text: value
    }));
});
/*
//способ 2
var new_options = '';
$.each(newOptions, function(key, value) {
    new_options += '' + value + '';
});
$('#my_select').html(new_options);
//способ 3
var select = $('#my_select');
if(select.prop) {
    var options = select.prop('options');
}
else {
    var options = select.attr('options');
}
$.each(newOptions, function(val, text) {
    options[options.length] = new Option(text, val);
});*/
