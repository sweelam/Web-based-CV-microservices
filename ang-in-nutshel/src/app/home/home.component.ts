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
  userId: number;

  constructor(private homeService: HomeService, private loginService: LoginService) {
    this.userInfo = new UserInfo(null, null, null, null, null, null, null);
  }

  ngOnInit() {
    this.userId = +sessionStorage.getItem("userId");
    this.fetchUserInfo(this.userId);
  }

  fetchUserInfo(userId: number): void {
    this.fetchUserHome();

      this.homeService.getFullName(userId).subscribe(data => {
        this.fullname = data;
      });

      this.homeService.getInfoDetails(userId).subscribe(data => {
        this.userInfo = new UserInfo(
          data['id'],
          data['fullName'],
          data['mobile'],
          data['email'],
          data['dateOfBirth'],
          data['address'],
          data['userTitle']);
      });

      this.homeService.getJobInfo(userId).subscribe(data => {
        this.jobInfo = JSON.parse(data);
      });

      this.fetchUserSkills(userId);
  }

  fetchUserSkills(accId): void {
    this.homeService.getUserSkills(accId).subscribe(data => {
      let skills: any[] = data;

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

  logout() {
    this.loginService.logout();
  }
}
