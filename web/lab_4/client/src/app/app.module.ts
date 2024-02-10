import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {SharedDataService} from './shared-data.service';
import {RouterModule, Routes} from "@angular/router";
import {StartComponent} from "./components/start/start.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {MainComponent} from "./components/main/main.component";
import {HeaderComponent} from "./components/header/header.component";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {CommonModule} from "@angular/common";

const appRoutes: Routes = [
    {path: '', component: StartComponent},
    {path: 'log', component: LoginComponent},
    {path: 'reg', component: RegisterComponent},
    {path: 'main', component: MainComponent}
];

@NgModule({
    declarations: [],
    imports: [BrowserModule,
        FormsModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        RouterModule.forRoot(appRoutes),
        CommonModule],
    providers: [SharedDataService],
    bootstrap: [AppComponent],
})
export class AppModule {
}
