let numX, numY, numR;
const form = document.getElementById('form');
let hiddenDivs = document.querySelectorAll(".hidden");
form.addEventListener('submit', validateForm);

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
    let fail = document.getElementById("errorX");
    let xFields = document.querySelectorAll('input[type="checkbox"]:checked');
    let xField;
    if (xFields.length === 1) {
        xField = xFields[0];
        numX = xField.value.replace(',', '.');
        if (!isNumeric(numX) || !numX) {
            fail.classList.remove("hidden");
            fail.classList.add("visible");
            return false;
        } else {
            fail.classList.add("hidden");
            fail.classList.remove("visible");
            return true;
        }
    } else {
        fail.classList.remove("hidden");
        fail.classList.add("visible");
        return false;
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
    let rField = document.querySelector('input[type="radio"]:checked');
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