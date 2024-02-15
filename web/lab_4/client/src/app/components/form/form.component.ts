import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {SharedDataService} from "../../shared-data.service";
import {ElementService} from "../../element.service";
import {Element} from "../../element";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-form',
  standalone: true,
  templateUrl: './form.component.html',
  imports: [
    FormsModule
  ],
  styleUrl: './form.component.css'
})
export class FormComponent {
  R: number;
  Xval: string;
  Yval: string;

  constructor(private sharedDataService: SharedDataService, private elementService: ElementService, public point: Element) {}

  handleRChange() {
    this.sharedDataService.setRValue(this.R);
  }
  sendCheck(){
    this.point.x = this.Xval
    this.point.y = this.Yval
    this.point.r = String(this.R);
    this.elementService.addElement(this.point).subscribe(
      (response) =>{
        console.log(response);
      },
      (error) =>{
        console.log(error);
    }
    );
  }
  clear(){
    this.elementService.clearAllElements(this.R).subscribe(
      (response) =>{
        console.log(response);
      },
      (error) =>{
        console.log(error);
      }
    )
  }
}
