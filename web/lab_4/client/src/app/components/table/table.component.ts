import { Component } from '@angular/core';
import {DataService} from "../../data.service";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent {
  // tableData: any[];
  //
  // constructor(private dataService: DataService) { }
  //
  // ngOnInit(): void {
  //   this.dataService.getData().subscribe(data => {
  //     this.tableData = data;
  //   });
  // }
}
