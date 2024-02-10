import {Component, OnInit, ViewChild} from '@angular/core';
import {DataService} from "../../data.service";
import {CommonModule} from "@angular/common";
import {ElementService} from "../../element.service";
import {CanvasComponent} from "../canvas/canvas.component";
interface MyModel {
  result: string;
  x: string;
  y: string;
  r: string;
  time: string;
  scriptTime: string;
}

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [CommonModule, CanvasComponent],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent implements OnInit{
  tableData: any[];
  modelList: MyModel[] = [];
  @ViewChild(CanvasComponent) canvas: CanvasComponent;

  constructor(private elementService: ElementService, private element: Element) { }

  ngOnInit(): void {
    console.log("Load all points");
    this.elementService.getAllElements(this.element).subscribe(
        (response) => {
          console.log('Data sent successfully', response);
          const pointsData = response.pointsData?.point || [];
          // @ts-ignore
          const pointsArray = pointsData.map(point => ({
            result: point.result,
            x: point.x,
            y: point.y,
            r: point.r,
            time: point.time,
            scriptTime: point.executionTime
          }));
          this.modelList = [...this.modelList, ...pointsArray];
          this.modelList.forEach(point => {
            this.canvas.drawPoint(Number(point.x), Number(point.y), Boolean(point.r));
          });
          console.log(this.modelList);
        },
        (error) => {
          console.error('Error sending data', error);
        }
    );
  }
}
