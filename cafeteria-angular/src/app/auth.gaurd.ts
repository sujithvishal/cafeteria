import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import { AuthService } from "./services/auth/auth.service";


@Injectable({
  providedIn: 'root'
})
export class AuthGaurd implements CanActivate {

  constructor(private authService:AuthService,private router:Router){

  }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {

      if(this.authService.isAdmin()){
        return true;
      }

      this.router.navigate(['/home'])
      return false;
    }

}
@Injectable({
  providedIn: 'root'
})
export class UserGaurd implements CanActivate{
  constructor(private authService:AuthService,private router:Router){

  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {

    if(!this.authService.isAdmin()){
      return true;
    }

    this.router.navigate(['/admin'])
    return false;
  }

}
