const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

const centerX = canvas.width/2;
const centerY = canvas.height/2;
const axisLength = Math.min(centerX, centerY)+60;
const arrowSize = 3;
function drawXAxis() {
    ctx.beginPath();
    ctx.moveTo(centerX - axisLength, centerY);
    ctx.lineTo(centerX + axisLength, centerY);
    ctx.lineTo(centerX + axisLength - arrowSize, centerY - arrowSize);
    ctx.moveTo(centerX + axisLength, centerY);
    ctx.lineTo(centerX + axisLength - arrowSize, centerY + arrowSize);
    ctx.strokeStyle = 'black';
    ctx.lineWidth = 2;
    ctx.stroke();
}
function drawYAxis() {
    ctx.beginPath();
    ctx.moveTo(centerX, centerY - axisLength/2);
    ctx.lineTo(centerX, centerY + axisLength-70);
    ctx.moveTo(centerX, centerY - axisLength);
    ctx.strokeStyle = 'black';
    ctx.lineWidth = 3.5;
    ctx.stroke();
}
function drawPoint(x, y) {
    ctx.beginPath();
    ctx.arc(centerX + x, centerY - y, 2, 0, 2 * Math.PI);
    ctx.fillStyle = 'red';
    ctx.fill();
}

drawXAxis();
drawYAxis();

canvas.addEventListener('click', (event) => {
    const rect = canvas.getBoundingClientRect();
    const mouseX = event.clientX - rect.left;
    const mouseY = event.clientY - rect.top;

    // Преобразуем координаты клика в координаты системы координат
    const x = mouseX - centerX;
    const y = centerY - mouseY;

    // Отрисовываем точку
    drawPoint(x, y);
});