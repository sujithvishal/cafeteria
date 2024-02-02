import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environments";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import { Employee } from '../../models/employee';


const BASE_URL=environment['BASE_URL'];
@Injectable({
  providedIn: 'root'
})
export class AuthService {


  // @ts-ignore
  employee: Employee={};

  constructor(private http:HttpClient,private router:Router) { }

  login(id: string, password: string){

    const header={'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',}

    return this.http.post(BASE_URL+"employee/login",{'id':id,'password':password},{headers:header});
  }
  adminLogin(id: string, password: string){

    const header={'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',}

    return this.http.post(BASE_URL+"employee/admin/login",{'id':id,'password':password},{headers:header});
  }

  logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('employeeName');
    localStorage.removeItem('employeeId');
    localStorage.removeItem('admin')
    localStorage.removeItem("balance");
    if(!this.isLoggedIn()){

      this.router.navigate(["/login"])
        // .then(()=>{
        //   window.location.reload();
        // });
    }
  }

  isLoggedIn():boolean{

    return (localStorage.getItem("token") != null && localStorage.getItem("employeeId") != null && localStorage.getItem("employeeName") != null);
    // if(this.employee.token!=null)return true;
    // return false;
  }
  checkLogin(){
    if(!this.isLoggedIn()){

      this.router.navigate(["/login"])
        // .then(()=>{
        //   window.location.reload();
        // });
    }

  }
  checkUserLogin(){
    if(!this.isLoggedIn()){

      this.router.navigate(["/login"])
      // .then(()=>{
      //   window.location.reload();
      // });
    }
    console.log(this.employee)
    if(this.employee.role=='ADMIN'){
      this.router.navigate(["/admin"])
    }

  }

  isAdmin() {
    return this.employee?.role=='ADMIN';
  }
}
