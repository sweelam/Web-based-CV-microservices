import { Component, OnInit } from '@angular/core';
import { LoginService } from './login/login.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  accId: number;
  constructor(public loginService: LoginService,
    private location: Location) {
  }

  ngOnInit() {
  }

  back() {
    this.location.back();
  }
}
