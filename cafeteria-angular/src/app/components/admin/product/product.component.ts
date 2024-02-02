import { Component } from '@angular/core';
import {Product} from "../../../models/product";
import {ActivatedRoute, Router} from "@angular/router";
import {ApiService} from "../../../services/api/api.service";
import {NgToastService} from "ng-angular-popup";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
  edit:boolean=false;
  product={price:0,name:'',imageUrl:''};


  constructor(private route:ActivatedRoute,private router:Router,private apiService:ApiService,private toast:NgToastService) {
  }

  ngOnInit(){
    let id = this.route.snapshot.paramMap.get("id");
    if (id != null) {
      this.edit=true;
      this.apiService.getProductById(id).subscribe((response: any) => {
        this.product = response;

      }, (error: any) => {
        this.router.navigate(['/admin/products'])
      })
    }

  }


  update(){

    if(this.edit){
      this.apiService.updateProduct(<Product>this.product).subscribe((response)=>{
        this.toast.success({detail:"SUCCESS",summary:'Product Updated Successfully!',duration:2000})
        this.router.navigate(['/admin/products'])

      },(error)=>{
        this.toast.success({detail:"error",summary:'Failed to Update Product:(',duration:2000})

      })
    }else{
      this.apiService.addProduct(<Product>this.product).subscribe((response)=>{
        this.toast.success({detail:"SUCCESS",summary:'Product Added Successfully!',duration:2000})
        this.router.navigate(['/admin/products'])

      },(error)=>{
        this.toast.success({detail:"error",summary:'Failed to Add Product:(',duration:2000})

      })


    }

  }

}
