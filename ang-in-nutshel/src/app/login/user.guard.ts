import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { LoginService } from './login.service';
import { Router } from '@angular/router';

@Injectable()
export class UserGuard implements CanActivate {

  constructor(public loginService: LoginService, public router: Router) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot):
    Observable<boolean> | Promise<boolean> | boolean {


    let sameUser = state.url.includes("home/" + sessionStorage.getItem("userId"))
    if (state.url.includes("home/") && !sameUser) {
      this.router.navigate(['login']);
      return false;
    }


    if (sessionStorage.getItem("logged-in") != 'true') {
      this.router.navigate(['login']);
      return false;
    }

    return true;
  }
}
