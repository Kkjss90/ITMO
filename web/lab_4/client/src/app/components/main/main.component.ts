import {Component} from '@angular/core';
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
export class MainComponent {
  constructor(private router: Router) {
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }
  //   this.dataService.logoutUser().subscribe(
  //     (response) => {
  //       console.log(response);
  //     },
  //     (error) => {
  //       console.log(error);
  //     }
  //   )
  // }
}
