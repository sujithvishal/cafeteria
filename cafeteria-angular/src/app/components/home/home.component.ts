import { Component } from '@angular/core';
import {AuthService} from "../../services/auth/auth.service";
import {Router, RouterLink} from "@angular/router";
import {Product} from "../../models/product";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  isLoggedIn!:boolean;
  employeeName!:string;
  balance!:string;
  product:Product[]=[]
  constructor(private authService:AuthService,private router:Router) {
  }

  ngOnInit(){

    this.authService.checkUserLogin();
    if(this.authService.isAdmin())this.router.navigateByUrl('admin')
    // @ts-ignore
    this.employeeName=localStorage.getItem('employeeName');
    // @ts-ignore
    this.balance=localStorage.getItem('balance');

  }

}
