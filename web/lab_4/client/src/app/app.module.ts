import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { StartComponent } from './components/start/start.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { MainComponent } from './components/main/main.component';
import { HeaderComponent } from './components/header/header.component';
import { TableComponent } from './components/table/table.component';
import { CanvasComponent } from './components/canvas/canvas.component';

import { AppRoutingModule } from './app-routing.module';
import { CommonModule } from '@angular/common';

import { SharedDataService } from './shared-data.service';
import { ElementService } from './element.service';
import { DataService } from './data.service';
import {FormComponent} from "./components/form/form.component";

const appRoutes: Routes = [
  { path: '', component: StartComponent },
  { path: 'log', component: LoginComponent },
  { path: 'reg', component: RegisterComponent },
  { path: 'main', component: MainComponent }
];

@NgModule({
  declarations: [
    AppComponent],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    CommonModule,
  HeaderComponent],
  providers: [SharedDataService, ElementService, DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
