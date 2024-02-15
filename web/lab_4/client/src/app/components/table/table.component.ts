import {Component, OnInit, ViewChild, ElementRef, AfterViewInit} from '@angular/core';
import {CommonModule, NgFor} from "@angular/common";
import {ElementService} from "../../element.service";
import {CanvasComponent} from "../canvas/canvas.component";
import {SharedDataService} from "../../shared-data.service";
import {TableDataService} from "../../table-data-service.service";

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
  currentR: number;

  constructor(private elementService: ElementService, private elementRef: ElementRef, private sharedDataService: SharedDataService, private tableDataService: TableDataService) {
  }
  ngOnInit(): void {
    this.loadAll();
    this.sharedDataService.R$.subscribe((r) => {
      this.currentR = r;
    });
    this.tableDataService.clearTable$.subscribe(() => {
      this.clearTable();
    });
  }
clearTable(){
    this.tableData = [];
}
  updateTable(response:any) {
    console.log(response);
      const point: MyModel = {
        result: response.success,
        x: parseFloat(response.x),
        y: parseFloat(response.y),
        r: parseFloat(response.r),
        time: new Date(response.timestamp),
        scriptTime: new Date().getTime()
      };
      this.modelList.push(point);
      this.tableData.push(point);
    }

  loadAll() {
    {
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
}
