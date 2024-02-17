import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";

@Component({
    selector: 'app-start',
    standalone: true,
    templateUrl: './start.component.html',
    imports: [
        RouterLink
    ],
    styleUrl: './start.component.scss'
})
export class StartComponent implements OnInit {
    constructor(private router: Router) {
    }

    ngOnInit() {
        console.log(localStorage);
        this.updateTime();
        setInterval(() => {
            if (localStorage.getItem('refresh_token') != null) {
                this.router.navigate(['/main']);
            }
        }, 100);
        setInterval(() => {
            this.updateTime();
        }, 6000);
    }

    private updateTime() {
        const seconds: number = new Date().getSeconds();

        //Seconds
        document.getElementById("seconds")!.innerHTML = (seconds < 10 ? '0' : '') + seconds;

        // Minutes
        const minutes: number = new Date().getMinutes();
        document.getElementById("minutes")!.innerHTML = (minutes < 10 ? '0' : '') + minutes;

        // Hours
        const hours: number = new Date().getHours();
        document.getElementById("hours")!.innerHTML = (hours < 10 ? '0' : '') + hours;
    }
}
