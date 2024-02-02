import { Component } from '@angular/core';
import {AuthService} from "../../services/auth/auth.service";
import {Router, RouterLink} from "@angular/router";
import {NgToastService} from "ng-angular-popup";
import {CartService} from "../../services/cart/cart.service";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  isLoggedIn!:boolean;
  admin!:boolean;

  constructor(private authService:AuthService,private router:Router,private toast:NgToastService,private cartService:CartService) {
  }

  ngOnInit(){
    // this.isLoggedIn=this.authService.isLoggedIn();
    this.router.events.subscribe(event=>{
      this.isLoggedIn=this.authService.isLoggedIn();
      this.admin=this.authService.isAdmin();

    })

  }


  logout(){
    this.authService.logout();
    this.cartService.clearItems();
    this.toast.success({detail:"SUCCESS",summary:'LoggedOut Successfully!',duration:2000})
  }

}
