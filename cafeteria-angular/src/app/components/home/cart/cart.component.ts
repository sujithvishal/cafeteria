import { Component } from '@angular/core';
import {CartItem, CartService} from "../../../services/cart/cart.service";
import { Product } from '../../../models/product';
import {ApiService} from "../../../services/api/api.service";
import {Order} from "../orders/orders.component";
import {AuthService} from "../../../services/auth/auth.service";
import {NgToastService} from "ng-angular-popup";
import { Employee } from '../../../models/employee';


@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

  cartItems: CartItem[] = []
  total = 0

  constructor(private authService:AuthService,private cartService: CartService,private apiService:ApiService,private toast:NgToastService) {
  }

  ngOnInit() {
    this.authService.checkUserLogin();
    this.cartItems = this.cartService.getItems();
    this.total = this.cartService.getTotalPrice()

  }



  calculate(n1: number, n2: number) {
    return n1 * n2;
  }

  increment(product: Product){
    this.cartService.addItem(product)
    this.total=this.cartService.getTotalPrice()

  }
  decrement(product: Product){
    this.cartService.reduceCount(product)
    this.total=this.cartService.getTotalPrice()

  }

  remove(product: Product){
    this.cartService.removeItem(product);
    this.updateState();


  }

  placeOrder(){

    // @ts-ignore
    let order:Order={employeeId:localStorage.getItem('employeeId'),orderRequestItems:this.cartItems,total:this.total}

    this.apiService.placeOrder(order).subscribe((response)=>{
      this.cartService.clearItems();
      this.apiService.updateBalance().subscribe((response:Employee)=>{
        // @ts-ignore
        this.authService.employee.balance=response.balance;
        localStorage.setItem("balance",response.balance.toString());
      })
      this.updateState();

    });
    this.toast.success({detail:"SUCCESS",summary:'Order placed!',duration:2000})

  }

  updateState(){

    this.cartItems=this.cartService.getItems();
    this.total=this.cartService.getTotalPrice();

  }


}
