var req;
var isIE;

var completeField;
var completeTable;
var autoRow;

function init() {
    completeField = document.getElementById("shop");
    completeTable = document.getElementById("complete-table");
    autoRow = document.getElementById("auto-row");
    completeTable.style.top = getElementY(autoRow) + "px";
}

function doCompletion() {
    var url = "ajax/receipts/autocomplete?action=complete&id=" + /*escape*/encodeURI(completeField.value);
    req = initRequest();
    req.open("GET", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') !== -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}
function callback() {
    clearTable();
    if (req.readyState === 4) {
        if (req.status === 200) {
            parseMessages(req.responseXML);
        }
    }
}
function appendShop(name, id) {

    var row;
    var cell;
    var linkElement;

    if (isIE) {
        completeTable.style.display = 'block';
        row = completeTable.insertRow(completeTable.rows.length);
        cell = row.insertCell(0);
    } else {
        completeTable.style.display = 'table';
        row = document.createElement("tr");
        cell = document.createElement("td");
        row.appendChild(cell);
        completeTable.appendChild(row);
    }

    cell.className = "popupCell";

    linkElement = document.createElement("a");
    linkElement.className = "popupItem";
    linkElement.appendChild(document.createTextNode(name));
    $(linkElement).bind('click', function () {
        $(completeField).val(name);
    });
    alert(completeField.val());
    cell.appendChild(linkElement);
}
function getElementY(element) {

    var targetTop = 0;

    if (element.offsetParent) {
        while (element.offsetParent) {
            targetTop += element.offsetTop;
            element = element.offsetParent;
        }
    } else if (element.y) {
        targetTop += element.y;
    }
    return targetTop;
}
function clearTable() {
    if (completeTable.getElementsByTagName("tr").length > 0) {
        completeTable.style.display = 'none';
        for (var loop = completeTable.childNodes.length - 1; loop >= 0; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

function parseMessages(responseXML) {

    // no matches returned
    if (responseXML === null) {
        return false;
    } else {

        var shops = responseXML.getElementsByTagName("List")[0];
        if (shops.childNodes.length > 0) {
            /*completeTable.setAttribute("bordercolor", "black");
             completeTable.setAttribute("border", "1");*/

            for (var loop = 0; loop < shops.childNodes.length; loop++) {
                var shop = shops.childNodes[loop];
                var name = shop.getElementsByTagName("name")[0];
                var shopId = shop.getElementsByTagName("id")[0];
                appendShop(name.childNodes[0].nodeValue,
                    shopId.childNodes[0].nodeValue);
            }
        }
    }
}
