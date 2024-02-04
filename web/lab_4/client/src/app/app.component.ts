import { Component } from '@angular/core';
import {HeaderComponent} from "./components/header/header.component";
import {RouterOutlet} from "@angular/router";
import {StartComponent} from "./components/start/start.component";
@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  imports: [
    HeaderComponent,
    RouterOutlet,
    StartComponent
  ],
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'client';
}
