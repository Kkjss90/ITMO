import {Component, EventEmitter, Output, ViewChild} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {SharedDataService} from "../../shared-data.service";
import {ElementService} from "../../element.service";
import {Element} from "../../element";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {TableComponent} from "../table/table.component";
import {TableDataService} from "../../table-data-service.service";

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
  @Output() formSubmitted: EventEmitter<any> = new EventEmitter();

  constructor(private sharedDataService: SharedDataService, private elementService: ElementService, public point: Element, private tableDataService: TableDataService) {}

  clearTable() {
    this.tableDataService.clearTable();
  }

  validateInput(event: any) {
    const allowedChars = ['0', '1', '2', '3', '4', '5', '.', ','];
    const inputChar = event.key || String.fromCharCode(event.which || event.keyCode);

    if (!/^-?\d*\.?\d*$/.test(inputChar) && allowedChars.indexOf(inputChar) === -1) {
      event.preventDefault();
      return;
    }

    const value = event.target.value + inputChar;

    if ((value.match(/-/g) || []).length > 1 || (value.indexOf('-') !== -1 && event.target.selectionStart !== 0)) {
      event.preventDefault();
      return;
    }

    if ((value.match(/[.,]/g) || []).length > 1 || (value.indexOf('.') === 0 || value.indexOf(',') === 0)) {
      event.preventDefault();
      return;
    }

    if (parseFloat(value) < -5 || parseFloat(value) > 5) {
      event.preventDefault();
      return;
    }
  }
  handleRChange() {
    this.sharedDataService.setRValue(this.R);
  }
  sendCheck(){
    if (this.R>=0) {
      this.point.x = this.Xval;
      this.point.y = this.Yval;
      this.point.r = String(this.R);
      this.elementService.addElement(this.point).subscribe(
        (response) => {
         console.log(response);
         this.formSubmitted.emit(response);
        },
        (error) => {
          console.log(error);
        }
      );
    }
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
  protected readonly event = event;
}
