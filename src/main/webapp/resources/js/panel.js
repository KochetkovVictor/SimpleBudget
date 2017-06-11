$(function(){
    $('.panel').tabSlideOut({							//Класс панели
        tabHandle: '.handle',						//Класс кнопки
        pathToTabImage: 'button.gif',				//Путь к изображению кнопки
        imageHeight: '122px',						//Высота кнопки
        imageWidth: '40px',						//Ширина кнопки
        tabLocation: 'right',						//Расположение панели top - выдвигается сверху, right - выдвигается справа, bottom - выдвигается снизу, left - выдвигается слева
        speed: 300,								//Скорость анимации
        action: 'click',								//Метод показа click - выдвигается по клику на кнопку, hover - выдвигается при наведении курсора
        topPos: '200px',							//Отступ сверху
        fixedPosition: false						//Позиционирование блока false - position: absolute, true - position: fixed
    });
});
