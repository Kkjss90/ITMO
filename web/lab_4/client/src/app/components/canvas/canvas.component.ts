import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {SharedDataService} from '../../shared-data.service';
import {NgIf} from "@angular/common";
import {ElementService} from "../../element.service";
import {Element} from "../../element";
import {DataService} from "../../data.service";
import {TableDataService} from "../../table-data-service.service";
import {ClearCanvasService} from "../../clear-canvas-service.service";
interface MyModel {
  result: boolean;
  x: number;
  y: number;
  r: number;
  time: Date;
  scriptTime: number;
}
@Component({
  selector: 'app-canvas',
  standalone: true,
  templateUrl: './canvas.component.html',
  imports: [
    NgIf
  ],
  styleUrls: ['./canvas.component.css']
})
export class CanvasComponent implements OnInit, AfterViewInit {
  @ViewChild('graph', {static: true}) canvasRef: ElementRef;
  context: CanvasRenderingContext2D;
  xAxisLabel = 'X';
  yAxisLabel = 'Y';
  xAxisScale = 0;
  yAxisScale = 0;
  currentR: number;
  isTextVisible: boolean = false;
  errorMessage: string = '';

  constructor(private sharedDataService: SharedDataService, private elementService: ElementService,public point: Element, private tableDataService: TableDataService,  private clearCanvasService: ClearCanvasService) {
  }

  ngAfterViewInit(): void {
    this.context = this.canvasRef.nativeElement.getContext('2d');
    this.draw();
  }

  ngOnInit(): void {
    this.sharedDataService.R$.subscribe((r) => {
      this.currentR = r;
      this.drawShapesByR(this.currentR);
      this.drawPointsByR();
    });
    this.clearCanvasService.clearCanvas$.subscribe(() => {
      this.drawShapesByR(this.currentR);
    });
  }

  draw() {
    if (this.context) {
      this.context.clearRect(0, 0, this.canvasRef.nativeElement.width, this.canvasRef.nativeElement.height);
      this.context.fillStyle = 'rgb(0,0,0)';
      const canvasWidth = this.canvasRef.nativeElement.width;
      const canvasHeight = this.canvasRef.nativeElement.height;

      this.xAxisScale = canvasWidth / 10;
      this.yAxisScale = canvasHeight / 10;

      const originX = canvasWidth / 2;
      const originY = canvasHeight / 2;

      this.context.beginPath();
      this.context.moveTo(0, originY);
      this.context.lineTo(canvasWidth, originY);
      this.context.stroke();

      this.context.beginPath();
      this.context.moveTo(originX, 0);
      this.context.lineTo(originX, canvasHeight);
      this.context.stroke();

      this.context.font = '14px Arial';
      this.context.fillText(this.xAxisLabel, canvasWidth - 15, canvasHeight / 2 - 5);
      this.context.fillText(this.yAxisLabel, canvasWidth / 2 + 5, 15);

      for (let i = -canvasWidth / 2; i < canvasWidth / 2; i += this.xAxisScale) {
        const scalePos = this.axesToCanvasCoordinates(i, 0);
        this.context.beginPath();
        this.context.moveTo(scalePos.x, scalePos.y - 5);
        this.context.lineTo(scalePos.x, scalePos.y + 5);
        this.context.stroke();
        this.context.fillText(String(this.rescaleXAxesCoordinate(i)), scalePos.x - 10, scalePos.y + 20);
      }

      for (let j = -canvasHeight / 2; j < canvasHeight / 2; j += this.yAxisScale) {
        const scalePos = this.axesToCanvasCoordinates(0, j);
        this.context.beginPath();
        this.context.moveTo(scalePos.x - 5, scalePos.y);
        this.context.lineTo(scalePos.x + 5, scalePos.y);
        this.context.stroke();
        this.context.fillText(String(this.rescaleYAxesCoordinate(j)), scalePos.x + 10, scalePos.y + 5);
      }
    }
  }

  axesToCanvasCoordinates(xAxes: number, yAxes: number) {
    const originX = this.canvasRef.nativeElement.width / 2;
    const originY = this.canvasRef.nativeElement.height / 2;
    const canvasX = originX + xAxes;
    const canvasY = originY - yAxes;
    return {x: canvasX, y: canvasY};
  }

  rescaleXAxesCoordinate(coordinate: number) {
    return coordinate / this.xAxisScale;
  }

  rescaleYAxesCoordinate(coordinate: number) {
    return coordinate / this.yAxisScale;
  }

  scaleXAxesCoordinate(coordinate: number) {
    return coordinate * this.xAxisScale;
  }

  scaleYAxesCoordinate(coordinate: number) {
    return coordinate * this.yAxisScale;
  }

  drawShapesByR(r: number) {
      if (this.context) {
        this.context.clearRect(0, 0, this.canvasRef.nativeElement.width, this.canvasRef.nativeElement.height);
        this.draw();

        // draw square
        const startPointInAxes = {x: 0, y: 0};
        const startPointInCanvas = this.axesToCanvasCoordinates(startPointInAxes.x, startPointInAxes.y);

        const endPointInAxes = {x: r / 2, y: r};
        const endScaledPointInAxes = {
          x: this.scaleXAxesCoordinate(endPointInAxes.x),
          y: this.scaleYAxesCoordinate(endPointInAxes.y)
        };

        this.context.fillStyle = 'rgba(255,242,0,0.75)';

        this.context.beginPath();
        this.context.fillRect(startPointInCanvas.x, startPointInCanvas.y, endScaledPointInAxes.x, -endScaledPointInAxes.y);

        // draw triangle
        const secondTrianglePointInAxes = {x: -r, y: 0};
        const thirdTrianglePointInAxes = {x: 0, y: -r};
        this.context.fillStyle = 'rgba(0,200,57,0.75)';
        this.drawTriangle({
          ctx: this.context,
          startPointInAxes: startPointInAxes,
          secondTrianglePointInAxes: secondTrianglePointInAxes,
          thirdTrianglePointInAxes: thirdTrianglePointInAxes
        });

        // draw 1/4 circle
        this.context.fillStyle = 'rgba(104,55,206,0.5)';
        const calculatedRadius = this.scaleXAxesCoordinate(Math.abs(r) / 2);
        let mirrorStartAngle;
        let mirrorEndAngle;
        if (r>=0) {
          mirrorStartAngle = -3 * Math.PI / 2;
          mirrorEndAngle = -2 * Math.PI;
        }else{
          mirrorStartAngle = -Math.PI/2;
          mirrorEndAngle = - Math.PI;
        }

        this.context.beginPath();
        this.context.arc(startPointInCanvas.x, startPointInCanvas.y, calculatedRadius, mirrorStartAngle, mirrorEndAngle, true);
        this.context.closePath();
        this.context.fill();

        // draw missing triangle
        const secondTrianglePointInAxesM = {x: r / 2, y: 0};
        const thirdTrianglePointInAxesM = {x: 0, y: -r / 2};
        this.drawTriangle({
          ctx: this.context,
          startPointInAxes: startPointInAxes,
          secondTrianglePointInAxes: secondTrianglePointInAxesM,
          thirdTrianglePointInAxes: thirdTrianglePointInAxesM
        });
        this.isTextVisible = false;
      }
  }

  drawTriangle({
                 ctx,
                 startPointInAxes,
                 secondTrianglePointInAxes,
                 thirdTrianglePointInAxes
               }: {
    ctx: CanvasRenderingContext2D;
    startPointInAxes: any;
    secondTrianglePointInAxes: any;
    thirdTrianglePointInAxes: any;
  }) {
    if (this.context) {
      const startPointInCanvas = this.axesToCanvasCoordinates(startPointInAxes.x, startPointInAxes.y);
      const secondScaledTrianglePointInAxes = {
        x: this.scaleXAxesCoordinate(secondTrianglePointInAxes.x),
        y: this.scaleYAxesCoordinate(secondTrianglePointInAxes.y)
      };
      const thirdScaledTrianglePointInAxes = {
        x: this.scaleXAxesCoordinate(thirdTrianglePointInAxes.x),
        y: this.scaleYAxesCoordinate(thirdTrianglePointInAxes.y)
      };
      const secondTrianglePointInCanvas = this.axesToCanvasCoordinates(
        secondScaledTrianglePointInAxes.x,
        secondScaledTrianglePointInAxes.y
      );
      const thirdScaledTrianglePointInCanvas = this.axesToCanvasCoordinates(
        thirdScaledTrianglePointInAxes.x,
        thirdScaledTrianglePointInAxes.y
      );

      ctx.beginPath();
      ctx.moveTo(startPointInCanvas.x, startPointInCanvas.y);
      ctx.lineTo(secondTrianglePointInCanvas.x, secondTrianglePointInCanvas.y);
      ctx.lineTo(thirdScaledTrianglePointInCanvas.x, thirdScaledTrianglePointInCanvas.y);
      ctx.fill();
    }
  }

  drawPoint(x: number, y: number, result: boolean) {
    const pointSize = 4;
    const scaledPoint = {x: this.scaleXAxesCoordinate(x), y: this.scaleYAxesCoordinate(y)};
    const pointOnCanvas = this.axesToCanvasCoordinates(scaledPoint.x, scaledPoint.y);
    if (result){
      this.context.fillStyle = 'green';
    }else {
      this.context.fillStyle = 'red';
    }
    this.context.fillRect(pointOnCanvas.x - pointSize / 2, pointOnCanvas.y - pointSize / 2, pointSize, pointSize);
  }

  handleCanvasClick(event: MouseEvent) {
    const rect = this.canvasRef.nativeElement.getBoundingClientRect();
    const xCanvas = event.clientX - rect.left;
    const yCanvas = event.clientY - rect.top;

    const xAxes = (xCanvas - this.canvasRef.nativeElement.width / 2) / this.xAxisScale;
    const yAxes = -(yCanvas - this.canvasRef.nativeElement.height / 2) / this.yAxisScale;

    this.point.x = String(xAxes);
    this.point.y = String(yAxes);
    this.point.r = String(this.currentR);

    this.elementService.addElement(this.point).subscribe(
      (response) => {
        console.log(response);
        this.drawPoint(xAxes, yAxes, response.success);
      },
      (error) => {
        console.log(error);
      }
    )

    console.log(`Clicked at point (${xAxes}, ${yAxes})`);
  }
  updateCanvas(response: any){
   this.drawPoint(response.x, response.y, response.success);
  }
  drawPointsByR(){
    this.elementService.getAllElements(this.currentR).subscribe(
      (response: any[]) => {
        response.forEach((responseData) => {
          const point: MyModel = {
            result: responseData.success,
            x: parseFloat(responseData.x),
            y: parseFloat(responseData.y),
            r: parseFloat(responseData.r),
            time: new Date(responseData.timestamp),
            scriptTime: new Date().getTime()
          };
          if(this.currentR == point.r){
            this.drawPoint(point.x, point.y, point.result);
          }
        });
      },
      (error) => {
        console.error('Error sending data', error);
      });
  }
  updateTableOnClick() {
    this.tableDataService.updateTable();
  }
}
