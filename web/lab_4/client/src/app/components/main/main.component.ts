import {Component, OnInit, ViewChild} from '@angular/core';
import {CanvasComponent} from "../canvas/canvas.component";
import {FormComponent} from "../form/form.component";
import {TableComponent} from "../table/table.component";
import {ElementService} from "../../element.service";
import {DataService} from "../../data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [
    CanvasComponent,
    FormComponent,
    TableComponent
  ],
  providers: [ElementService],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent implements OnInit{
  @ViewChild(FormComponent) form: FormComponent;
  @ViewChild(CanvasComponent) canvas: CanvasComponent;
  @ViewChild(TableComponent) table: TableComponent;
  constructor(private router: Router) {
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }
  updateComponents(response: any[]) {
    this.table.updateTable(response);
    this.canvas.updateCanvas(response);
  }

  ngOnInit(): void {
    setInterval(() => {
      if (localStorage.getItem("refresh_token") == null) {
        localStorage.clear();
        this.router.navigate(['']);
      }
    }, 1000);
  }
}
