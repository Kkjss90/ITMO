import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {Data} from "../../data";
import {DataService} from "../../data.service";
import {NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    RouterLink,
    NgIf,
    FormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit {
  loginValue: string = '';
  passwordValue: string = '';
  isTextVisible: boolean = false;
  errorMessage: string = '';

  constructor(public dataRegister: Data, private dataService: DataService, private router: Router) {
  }

  ngOnInit() {
    //this.dataService.sendOptionsRequest();
    if (localStorage.getItem('refreshToken')) {
      this.router.navigate(['/main']);
    }
  }

  checkInput() {
    if (this.dataRegister.password !== this.dataRegister.password2) {
      return false;
      this.errorMessage = 'Passwords are different';
    } else {
      return true;
    }
  }

  tryRegistration() {
    console.log(this.dataRegister);
    this.dataService.registerUser(this.dataRegister).subscribe(
      (response) => {
        console.log(response);
        this.isTextVisible = false;
        localStorage.setItem('username', this.dataRegister.username);
        //localStorage.setItem('sessionId', response.refreshToken);
        console.log('Data sent successfully', response);
        this.router.navigate(['/log']);
      },
      (error) => {
        if (error.status == 401) {
          this.errorMessage = "User with this name already exists.";
        } else {
          this.errorMessage = 'Something went wrong, try again.';
        }
        this.isTextVisible = true;
      }
    );
  }

}
