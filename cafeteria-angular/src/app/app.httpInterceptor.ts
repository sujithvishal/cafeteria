import {HttpEvent, HttpHandlerFn, HttpInterceptor, HttpInterceptorFn, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

export const authInterceptor: HttpInterceptorFn = (
  req: HttpRequest<any>,
  next: HttpHandlerFn
): Observable<HttpEvent<any>> => {

  console.log(req);
  if(!req.url.includes("login")){
    let token=localStorage.getItem("token")
    console.log(token)
    if (token) {
      const cloned = req.clone({
        setHeaders: {
          authorization: token,

        },  
      });
      console.log(cloned);

      return next(cloned);
    }

  }

  return next(req);

};
