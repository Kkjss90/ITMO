import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {CommonModule, NgFor} from "@angular/common";
import { ElementService } from "../../element.service";
import { CanvasComponent } from "../canvas/canvas.component";

interface MyModel {
  result: boolean;
  x: number;
  y: number;
  r: number;
  time: Date;
  scriptTime: number;
}

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [CommonModule, NgFor],
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  tableData: MyModel[] = [];
  modelList: MyModel[] = [];
  @ViewChild(CanvasComponent) canvas: CanvasComponent;

  constructor(private elementService: ElementService, private elementRef: ElementRef) { }

  ngOnInit(): void {
    console.log("Load all points");
    this.elementService.getAllElements(this.elementRef.nativeElement).subscribe(
      (response: any[]) => {
        console.log(response);
        response.forEach((responseData) => {
          console.log('Data sent successfully', responseData);
          const point: MyModel = {
            result: responseData.success,
            x: parseFloat(responseData.x),
            y: parseFloat(responseData.y),
            r: parseFloat(responseData.r),
            time: new Date(responseData.timestamp),
            scriptTime: new Date().getTime()
          };
          this.modelList.push(point);
          //this.canvas.drawPoint(point.x, point.y, point.result);
          this.tableData.push(point);
        });
      },
      (error) => {
        console.error('Error sending data', error);
      }
    );
    console.log(this.tableData, this.modelList);
  }
}
