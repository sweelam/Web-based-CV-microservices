import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import 'rxjs/add/operator/map';

import { API } from '../app-constants/Constants';
import { Router } from '@angular/router';
import { SwalShowUtil } from '../error-handler/error-show-util';
import { ErrorHandler } from '../error-handler/error-handler';


@Injectable()
export class LoginService {
  isLoggedIn = false;
  redirectURL: string;
  error: string;
  hide: boolean = false;

  public userId: number;
  setUserId(userId: number) {
    this.userId = userId;
  }

  getUserId() {
    return this.userId;
  }

  constructor(private http: HttpClient, private router: Router) { }

  authLogin(username: string, password: string) {

    let request = {
      "username": username,
      "password": password
    }

    this.http.post(API.ROOT + "/auth-service/api/auth/user/login-form", request)
      .subscribe((data: any) => {
        this.login(data);
      }, (error: ErrorHandler) => this.showError(error));
  }


  private showError(error: ErrorHandler) {
    if (error.status == 404)
      SwalShowUtil.popupError('Error', 'Username or Password is not correct', 'OK', 'error');
    else
      SwalShowUtil.popupError('Error', error.error.message, 'OK', 'error');
  }

  private login(data: any) {
    this.isLoggedIn = true;
    this.hide = !this.isLoggedIn;

    const userId = data.userId;
    this.setUserId(userId);

    sessionStorage.setItem("logged-in", this.isLoggedIn + '');
    sessionStorage.setItem("userId", userId);

    this.router.navigate(['/home', this.getUserId()]);
  }

  logout() {
    sessionStorage.clear();
    location.reload();
    this.router.navigate(['/login']);
  }

}
