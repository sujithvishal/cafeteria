import { Injectable } from '@angular/core';
import {Product} from "../../models/product";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  products:CartItem[]=[]

  constructor() { }

  addItem(product:Product){
    let contains = this.products.find((c)=>c.product.id==product.id);
    if(contains){
      contains.count++;
    }else{
      let item:CartItem=new CartItem(product,1);
      this.products.push(item);
    }

  }

  removeItem(product:Product){
    this.products=this.products.filter((p)=>p.product.id!=product.id)
  }

  reduceCount(product:Product){
    let item=this.products.find((c)=>c.product.id==product.id)
    if(item&&item.count>1){
      item.count--;
    }

  }
  getItems(){
    return this.products;
  }

  getTotalPrice(){
    let total=0;
    for(let item of this.products){
      total+=(item.count*item.product.price);
    }
    return total;
  }
  clearItems(){
    this.products=[]
  }



}

export class CartItem{
  product!:Product;
  count!:number;

  constructor(a: Product, b: number) {
    this.product=a;
    this.count=b;

  }


}
