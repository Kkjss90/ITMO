const MIN_X = -4;
const MAX_XY = 4;
const MIN_Y = -3;
const MIN_COORD = 280;
const MAX_COORD = 751;
const TO_RECALC_COORD = 160;
const TO_RECALC_R = 300;

document.addEventListener('DOMContentLoaded', function () {
    const svg = document.querySelector('svg');
    svg.addEventListener('click', function (event) {
        let x = event.clientX;
        let y = event.clientY;
        let point = svg.createSVGPoint();
        point.x = x;
        point.y = y;
        let transformedPoint = point.matrixTransform(svg.getScreenCTM().inverse());
        checkClick(transformedPoint.x, transformedPoint.y);
        let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        circle.setAttribute("cx", transformedPoint.x);
        circle.setAttribute("cy", transformedPoint.y);
        circle.setAttribute("r", "3");
        circle.setAttribute("fill", "red");
        svg.appendChild(circle);
    });

    function checkClick(x, y) {
        console.log(x, y);
        if (!((x >= MIN_COORD && x <= MAX_COORD) && (y >= MIN_COORD && y <= MAX_COORD))) {
            alert('Вы не попали в область');
            return;
        }
        const r = document.querySelector('input[name="R"]:checked');
        if (!r) {
            alert('R не выбран');
            return;
        }
        var radius = r.value;
        var toSendX = parseFloat(((x - TO_RECALC_COORD) / TO_RECALC_R * radius).toFixed(5));
        var toSendY = parseFloat((TO_RECALC_COORD - y) / TO_RECALC_R * radius).toFixed(5);
        if (!(toSendX >= MIN_X && toSendX <= MAX_XY && toSendY >= MIN_Y && toSendY <= MAX_XY)) {
            alert('Выход значений за пределы допустимого');
            return;
        }
        $.ajax({
            type: "POST",
            url: ctx+"/ControllerServlet",
            async: false,
            data: {"X": toSendX, "Y": toSendY, "R": radius},
            success: function (data) {
                window.location.replace(ctx+'/index.jsp');
            },
            error: function (xhr, textStatus, err) {
                console.log("readyState: " + xhr.readyState + "\n" +
                    "responseText: " + xhr.responseText + "\n" +
                    "status: " + xhr.status + "\n" +
                    "text status: " + textStatus + "\n" +
                    "error: " + err);
            }
        });
    }
})
function drawPoint(x,y, radius){
    const TO_RECALC_COORD = 160;
    const TO_RECALC_R = 300;
    const svg = document.querySelector('svg');
    var resX = (x + TO_RECALC_COORD) * TO_RECALC_R / radius;
    var resY = (TO_RECALC_COORD + y) * TO_RECALC_R / radius;
    let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    circle.setAttribute("cx", resX);
    circle.setAttribute("cy", resY);
    circle.setAttribute("r", "3");
    circle.setAttribute("fill", "red");
    svg.appendChild(circle);
    console.log("Отрисовано");
}
