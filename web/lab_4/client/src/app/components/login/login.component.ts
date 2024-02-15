import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {Data} from "../../data";
import {DataService} from "../../data.service";
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    RouterLink,
    FormsModule,
    NgIf
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{
  loginValue: string = '';
  passwordValue: string = '';
  isTextVisible: boolean = false;
  errorMessage: string = '';

  constructor(public dataLogin: Data, private dataService: DataService, private router: Router) {
  }

  ngOnInit() {
    // if (localStorage.getItem('refresh_token')){
    //   this.router.navigate(['/main']);
    // }
  }

  tryLogin(){
    console.log(this.dataLogin);
    this.dataService.loginUser(this.dataLogin).subscribe(
        (response) => {
          console.log(response);
          this.isTextVisible = false;
            localStorage.setItem('username', this.dataLogin.username);
            localStorage.setItem('refresh_token', response.refreshToken);
            localStorage.setItem('sessionId', response.id_user);
            console.log(localStorage);
            console.log('Data sent successfully', response);
            this.router.navigate(['/main']);
        },
        (error) => {
          if(error.status == 401){
            this.errorMessage = 'No users with this login. Or wrong password.';
          }else {
            this.errorMessage = 'Something went wrong, try again.';
          }
          this.isTextVisible = true;
        }
    );
  }

}
