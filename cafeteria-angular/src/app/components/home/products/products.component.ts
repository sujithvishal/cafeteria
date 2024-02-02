import {Component, Input} from '@angular/core';
import {Product} from "../../../models/product";
import {ApiService} from "../../../services/api/api.service";
import {CartService} from "../../../services/cart/cart.service";
import {AuthService} from "../../../services/auth/auth.service";
import {NgToastService} from "ng-angular-popup";
import {Route, Router} from "@angular/router";
@Component({
  selector: 'app-products',
  standalone: true,
  imports: [],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {

  products:Product[]=[];
  isAdmin=false;

  constructor(private authService:AuthService,private apiService:ApiService,private cartService:CartService,private toast:NgToastService,private router:Router){

  }
  ngOnInit(){
    this.authService.checkLogin();
    this.apiService.getAllProducts().subscribe(response=>{
      this.products=response;
    })
    this.isAdmin=this.authService.isAdmin();


    }

  addToCart(product: Product) {

    this.cartService.addItem(product);
    this.toast.success({detail:"SUCCESS",summary:'Item added to cart!',duration:2000})

  }


  edit(product:Product) {

    this.router.navigate(['/admin/product',product.id]);

  }

  delete(product:Product) {

    if(confirm("Are you sure to delete this item?")){
    this.apiService.deleteProduct(product.id).subscribe((repsonse)=>{

      this.toast.success({detail:"SUCCESS",summary:'Item Deleted',duration:2000})
      this.apiService.getAllProducts().subscribe(response=>{
        this.products=response;
      })
    });
    }


  }
}
