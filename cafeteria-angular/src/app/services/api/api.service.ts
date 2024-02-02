import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../../models/product";
import {environment} from "../../../environments/environments";
import {Order} from "../../components/home/orders/orders.component";
import { AuthService } from '../auth/auth.service';
import { Employee } from '../../models/employee';



const BASE_URL=environment['BASE_URL'];
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  products=[];
   header={'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
     'Access-Control-Allowed-Methods':'*',
     'Access-Control-Exposed-Headers':'*',
   }


  constructor(private http:HttpClient,private authService:AuthService) { }


  getAllProducts(){

    return this.http.get<Product[]>(BASE_URL+"products",{headers:this.header});
  }

  getOrders(id:string){

    return this.http.get<Order[]>(BASE_URL+"orders/employee?id="+id,{headers:this.header});
  }

  getPendingOrders(){

     return this.http.get<Order[]>(BASE_URL+"orders/pending",{headers:this.header})
  }

  placeOrder(order: Order){
     console.log(order)

    return this.http.post(BASE_URL+"orders",order,{headers:this.header})
  }

  updateOrder(order: Order) {

    return this.http.put(BASE_URL+"orders?id="+order.id+"&status=prepared",{},{headers:this.header})
  }

  getProductById(id:string){
     return this.http.get<Product>(BASE_URL+"products/"+id,{headers:this.header});
  }

  updateProduct(product:Product){
     return this.http.put(BASE_URL+"products",product,{headers:this.header});

  }

  addProduct(product:Product){
     return this.http.post(BASE_URL+"products",product,{headers:this.header});
  }

  deleteProduct(id:string){
     return this.http.delete(BASE_URL+"products/"+id,{headers:this.header,responseType: 'text'});

  }

  updateBalance() {
    let id = this.authService.employee.id;
    return this.http.get<Employee>(BASE_URL+"employee/"+id,{headers:this.header});
  }
}
