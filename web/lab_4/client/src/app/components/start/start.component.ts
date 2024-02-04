import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-start',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './start.component.html',
  styleUrl: './start.component.scss'
})
export class StartComponent implements OnInit {
  ngOnInit() {
    this.updateTime();

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
