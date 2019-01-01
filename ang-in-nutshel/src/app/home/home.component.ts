import { Component, OnInit } from '@angular/core';

import { HomeService } from './home.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserInfo } from './user';
import { LoginService } from '../login/login.service';

class UserSkills {
  constructor(public id: number, public desc: string) { }
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  profile: string;
  fullname = "";
  userInfo: UserInfo;
  userSkills: UserSkills[] = [];
  jobInfo: any[] = [];
  paramValue = '';

  constructor(private homeService: HomeService, 
              private route: ActivatedRoute, 
              private loginService: LoginService) {
    this.userInfo = new UserInfo(null, null, null, null, null, null, null);
  }

  ngOnInit() {
    this.fetchUserInfo();
  }
  
  fetchUserInfo(): void {
    // Subscribe URL parameter
    this.route.params.subscribe(params => {
      this.paramValue = params['accId'];
      
      this.fetchUserHome();

      this.homeService.getFullName(this.paramValue).subscribe(data => {
        this.fullname = data;
      });

      this.homeService.getInfoDetails(this.paramValue).subscribe(data => {
        this.userInfo = new UserInfo(data['id'],
          data['fullname'],
          data['mobile'],
          data['email'],
          data['dateOfBirth'],
          data['address'],
          data['userTitle']);
      });

      this.homeService.getJobInfo(this.paramValue).subscribe(data => {
        this.jobInfo = JSON.parse(data);
      });

      this.fetchUserSkills();

    });
  }

  fetchUserSkills(): void {
    debugger
    this.homeService.getUserSkills().subscribe(data => {
      let skills : any[] = data;
      
      skills.forEach(elem => {
        this.userSkills.push(elem['userSkills']['skills']);
      });
    });
  }

  fetchUserHome(): void {
    this.homeService.home().subscribe(data => {
      this.profile = data;
    });
  }
}
