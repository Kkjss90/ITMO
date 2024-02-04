import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {SharedDataService} from "../../shared-data.service";

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

  constructor(private sharedDataService: SharedDataService) {}

  handleRChange() {
    this.sharedDataService.setRValue(this.R);
  }
}
