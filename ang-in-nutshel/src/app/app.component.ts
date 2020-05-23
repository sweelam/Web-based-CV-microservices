import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators/switchMap';
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
  constructor(private route: ActivatedRoute,
    private router: Router, public loginService: LoginService,
    private location: Location) { }

  ngOnInit() {

  }

  onSetupClick() {
    this.router.navigate(['/setup', this.loginService.getUserId()], {skipLocationChange: true});
  }

  back() {
    this.location.back();
  }
}
