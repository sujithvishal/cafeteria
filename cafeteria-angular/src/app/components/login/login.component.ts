import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {AuthService} from "../../services/auth/auth.service";
import {Router} from "@angular/router";
import {routes} from "../../app.routes";
import {NgToastService} from "ng-angular-popup";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginFailed=false;


  constructor(private authService:AuthService,private router:Router,private toast:NgToastService){

}

  user:User=new User('','');
  authenticationError=false;

  ngOnInit(){
    let loggedIn=this.authService.isLoggedIn();
    if(loggedIn)this.router.navigateByUrl("/home")
  }

  login(){

      // @ts-ignore
      this.authService.login(this.user.id,this.user.password).subscribe((response:loggedInUser)=>{
          console.log(response);

          localStorage.setItem('token',response.token);
          localStorage.setItem('employeeId',response.employeeId);
          localStorage.setItem('employeeName',response.employeeName);
          localStorage.setItem('admin','false');
          localStorage.setItem("balance",response.balance);

          this.authService.employee.name=response.employeeName;
          this.authService.employee.id=response.employeeId;
          this.authService.employee.balance=response.balance;
          this.authService.employee.token=response.token;
          this.authService.employee.role=response.role;

          if(response.role=="ADMIN"){
            localStorage.setItem('admin','true');
            this.router.navigate(['/admin'])
          }

          this.router.navigate(["/home"])
          // .then(()=>{
          //   window.location.reload();
          // });
          this.toast.success({detail:"SUCCESS",summary:'User LoggedIn Successfully!',duration:2000})
        },
        (error)=>{
          console.log(error)
          this.loginFailed=true;
        });
    }






}

class User{
  constructor(s: string, s2: string) {
    this.id=s;

    this.password=s2;

  }

  id!:string;
  password!:string;
}

// export class loggedInUser{
//   employeeId!:string;
//   employeeName!:string;
//   token!:string;
//   balance!: string;
//
// }
