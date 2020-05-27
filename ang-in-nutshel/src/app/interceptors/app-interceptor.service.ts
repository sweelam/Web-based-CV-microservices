import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch';

import swal from 'sweetalert2/dist/sweetalert2.js'
import { ErrorHandler } from '../error-handler/error-handler';

@Injectable()
export class AppInterceptorService implements HttpInterceptor {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //send the newly created request
    return next.handle(req)
      .catch((error: ErrorHandler, caught) => {
        //intercept the respons error and displace it to the console
        console.log("Error Occurred");

        if (error.status == 500) {
          swal({
            title: 'Error',
            text: 'Something goes wrong , please try again or call system admin',
            confirmButtonText: 'OK',
            type: 'error'
          });
          return Observable.empty();
        }

        //return the error to the method that called it
        return Observable.throw(error);
      }) as any;
  }

}
