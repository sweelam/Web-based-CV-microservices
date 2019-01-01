import { Component, OnInit, AfterContentInit, Renderer2, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';

import { ErrorShowUtil } from '../error-handler/error-show-util';
import { ErrorHandler } from '../error-handler/error-handler';
import { LoginService } from './login.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, AfterContentInit {
  errorMessage: string;
  error: string;

  @ViewChild('password') password: ElementRef;

  constructor(private login: LoginService, private router: Router, private renderer: Renderer2, private location: Location) { }

  ngOnInit() {
    if (sessionStorage.length > 0)
      sessionStorage.clear();

    if (this.location.isCurrentPathEqualTo('/login'))
      this.login.hide = true
  }

  ngAfterContentInit() {

  }

  onSubmit(loginForm) {
    if (loginForm.username !== '' && loginForm.password !== '') {
      this.login.authLogin(loginForm.username, loginForm.password);
    } else {
      ErrorShowUtil.popupError('Error', 'Username and Password are required', 'OK', 'error');
    }
  }

}
