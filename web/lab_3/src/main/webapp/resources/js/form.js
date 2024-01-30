let numX, numY, numR;
const form = document.getElementById('form');
let hiddenDivs = document.querySelectorAll(".hidden");
form.addEventListener('submit', validateForm);
const selectMenu = document.getElementById('form:selectMenu');

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function hide() {
    hiddenDivs.forEach(element => {
        element.classList.remove("visible");
        element.classList.add('hidden');
    });
}

function validateX() {
    const MIN = -4;
    const MAX = 4;
    let fail = document.getElementById("errorX");
    let xField = document.getElementById('X');
    numX = xField.value.replace(',', '.');
        if (!isNumeric(numX) || numX > MAX || numX < MIN) {
            fail.classList.remove("hidden");
            fail.classList.add("visible");
            return false;
        } else {
            fail.classList.add("hidden");
            fail.classList.remove("visible");
            return true;
        }
}

function validateY() {
    const MIN = -3;
    const MAX = 3;
    let fail = document.getElementById("errorY");
    let yField = document.getElementById('Y');
    numY = yField.value.replace(',', '.');
    if (!isNumeric(numY) || numY > MAX || numY < MIN) {
        fail.classList.remove("hidden");
        fail.classList.add("visible");
        return false;
    } else {
        fail.classList.add("hidden");
        fail.classList.remove("visible");
        return true;
    }
}

function validateR() {
    let fail = document.getElementById("errorR");
    let rField = document.getElementById('form:selectMenu').value;
    numR = rField.value.replace(',', '.');
    if (!isNumeric(numR) || !numR) {
        fail.classList.remove("hidden");
        fail.classList.add("visible");
        return false;
    } else {
        fail.classList.add("hidden");
        fail.classList.remove("visible");
        return true;
    }
}

function validateForm(event) {
    if (!(validateX() && validateY() && validateR())) {
        event.preventDefault();
        return false;
    }
    return true;
}
selectMenu.addEventListener('change', function () {
    const selectedValue = selectMenu.value;
    console.log(`Выбранное R: ${selectedValue}`);

    // Вызов функции для отрисовки форм на основе выбранного значения R
    drawShapesByR(+selectedValue);

    for (var i = 0; i < resultList.length; i++) {
        console.log("start");
        if (parseFloat(resultList[i].r) === parseFloat(selectedValue)) {
            console.log("draw");
            // Вызов функции для отрисовки точек
            drawPoint(resultList[i].x, resultList[i].y, resultList[i].res);
        }
    }
});