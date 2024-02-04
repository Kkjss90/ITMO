import { Component } from '@angular/core';
import {CanvasComponent} from "../canvas/canvas.component";
import {FormComponent} from "../form/form.component";

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [
    CanvasComponent,
    FormComponent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

}
