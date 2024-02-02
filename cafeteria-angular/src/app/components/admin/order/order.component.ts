import { Component } from '@angular/core';
import {AuthService} from "../../../services/auth/auth.service";
import {ApiService} from "../../../services/api/api.service";
import {Order} from "../../home/orders/orders.component";
import {DatePipe, JsonPipe} from "@angular/common";
import {of} from "rxjs";

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [
    DatePipe,
    JsonPipe
  ],
  templateUrl: './order.component.html',
  styleUrl: './order.component.css'
})
export class OrderComponent {
  orders: Order[] = [];


  constructor(private authService: AuthService, private apiService: ApiService) {
  }

  ngOnInit() {
    this.authService.checkLogin();

    // @ts-ignore
    this.apiService.getPendingOrders().subscribe((response) => {
      console.log(response);

      this.orders = response;

    });


  }

  updateOrder(order:Order) {

    if(confirm("Are you sure this order is prepared?")){

    this.apiService.updateOrder(order).subscribe((response)=>{
      // @ts-ignore
      this.apiService.getPendingOrders().subscribe((response) => {
        this.orders = response;
      });

    })
    }

  }
}
