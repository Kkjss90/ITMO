const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

const canvasWidth = canvas.clientWidth;
const canvasHeight = canvas.clientHeight;

const scaleX = 10;
const scaleY = 10;

const xAxis = canvasWidth/2;
const yAxis = canvasHeight/2;

ctx.beginPath();
ctx.strokeStyle = "lightgrey";

for (let i = 0; i < canvasWidth; i+=scaleX) {
    ctx.moveTo(i,0);
    ctx.lineTo(i, canvasHeight);
}
for (let i = 0; i < canvasHeight; i+=scaleY) {
    ctx.moveTo(0,i);
    ctx.lineTo(canvasWidth, i);
}
ctx.stroke();
ctx.closePath();

ctx.beginPath();
ctx.strokeStyle = "black";
ctx.moveTo(xAxis, 0);
ctx.lineTo(xAxis, canvasHeight);
ctx.moveTo(0, yAxis);
ctx.lineTo(canvasWidth, yAxis);

ctx.stroke();
ctx.closePath();
