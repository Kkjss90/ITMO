let numX, numY, numR;
const form = document.getElementById('form');
let hiddenDivs = document.querySelectorAll(".hidden");
form.addEventListener('submit', validateForm);

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function hide(){
        hiddenDivs.forEach(element => {
            element.classList.remove("visible");
            element.classList.add('hidden');
          });
}

function validateX() {
    const MAX = 5;
    const MIN = -5;
    let fail = document.getElementById("errorX");
    let xField = document.getElementById("x");
    numX = xField.value.replace(',', '.');
    if (isNumeric(numX) && (numX > MAX || numX < MIN)) {
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
    let fail = document.getElementById("errorY");
    let yField = document.getElementById('y');
    if (!yField) {
        fail.classList.remove("hidden");
        fail.classList.add("visible");
        return false;
    }
    numY = yField.value;
    fail.classList.add("hidden");
    fail.classList.remove("visible");
    return true;
}

function validateR() {
    const MAX = 5;
    const MIN = 2;
    let fail = document.getElementById("errorR");
    let rField = document.getElementById('r');
    numR = rField.value.replace(',', '.');
    if (isNumeric(numR) && (numR > MAX || numR < MIN)) {
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
    if(!(validateX() && validateY() && validateR())){
        event.preventDefault();
        return false;
    }
    return true;
}

function send() {
    if (validateForm()) {
        let request = new XMLHttpRequest();
        let formData = new FormData()
        formData.append("X", numX);
        formData.append("Y", Y);
        formData.append("R", R);
        formData.append('timezone', new Date().getTimezoneOffset());
        fetch(`send.php?x=${xValue}&y=${yValue}&r=${rValue}`, {
            method: "GET"
        })
    }
}