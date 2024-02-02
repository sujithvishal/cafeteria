import { Component } from '@angular/core';
import {CartItem} from "../../../services/cart/cart.service";
import {ApiService} from "../../../services/api/api.service";
import {AuthService} from "../../../services/auth/auth.service";

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent {
  orders:Order[]=[];


  constructor(private authService:AuthService,private apiService:ApiService) {
  }

  ngOnInit(){
    this.authService.checkUserLogin();
    let id = localStorage.getItem('employeeId');
    console.log("gonna fetch")
    // @ts-ignore
   this.apiService.getOrders(id).subscribe((response)=>{
     this.orders=response;
   });

  }


}



export interface Order{
  id:string;
  employeeId:string;
  items:CartItem[];
  total:number;
  status:string;
  date:Date;

}
