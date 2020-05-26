import { Component, OnInit, AfterContentInit, Renderer2, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';

import { SwalShowUtil } from '../error-handler/error-show-util';
import { ErrorHandler } from '../error-handler/error-handler';
import { LoginService } from './login.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  errorMessage: string;
  error: string;

  @ViewChild('password') password: ElementRef;

  constructor(public loginService: LoginService, private location: Location) { }

  ngOnInit() {
    if (sessionStorage.length > 0)
      sessionStorage.clear();

    if (this.location.isCurrentPathEqualTo('/login'))
      this.loginService.hide = true
  }

  onSubmit(loginForm) {
    if (loginForm.username !== '' && loginForm.password !== '') {
      this.loginService.authLogin(loginForm.username, loginForm.password);
    } else {
      SwalShowUtil.popupError('Error', 'Username and Password are required', 'OK', 'error');
    }
  }

}
