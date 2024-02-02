import { Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {ProductsComponent} from "./components/home/products/products.component";
import {MenuComponent} from "./components/home/menu/menu.component";
import {OrdersComponent} from "./components/home/orders/orders.component";
import {CartComponent} from "./components/home/cart/cart.component";
import {AdminComponent} from "./components/admin/admin.component";
import {OrderComponent} from "./components/admin/order/order.component";
import {ProductComponent} from "./components/admin/product/product.component";
import {AuthGaurd,UserGaurd} from "./auth.gaurd";
export const routes: Routes = [{
  path:'login',component:LoginComponent
},{
  path:'home',component:HomeComponent,canActivate:[UserGaurd]
},{
  path:'products',component:ProductsComponent,canActivate:[UserGaurd]
},{
  path:'menu',component:MenuComponent,canActivate:[UserGaurd]
},{
  path:'orders',component:OrdersComponent,canActivate:[UserGaurd]
},{
  path:'cart',component:CartComponent,canActivate:[UserGaurd]
},
  {
    path:'',redirectTo:'login',pathMatch:"full"
  },{
  path:"admin",component:AdminComponent,canActivate:[AuthGaurd]
  },{
  path:"admin/orders",component:OrderComponent,canActivate:[AuthGaurd]
  },
  {
    path:"admin/products",component:ProductsComponent,canActivate:[AuthGaurd]

  },
  {
    path:"admin/product",component:ProductComponent,canActivate:[AuthGaurd]
  },
  {
    path:"admin/product/:id", component:ProductComponent,canActivate:[AuthGaurd]
  },
  {
    path:'**',component:LoginComponent
  }


];
