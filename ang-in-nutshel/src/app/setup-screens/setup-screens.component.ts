import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';

import { SetupScreensService } from './setup-screens.service'
import { LoginService } from '../login/login.service';
import { UserJob } from './user';
import { ErrorHandler } from '../error-handler/error-handler';
import { ErrorShowUtil } from '../error-handler/error-show-util';

@Component({
  selector: 'app-setup-screens',
  templateUrl: './setup-screens.component.html',
  styleUrls: ['../login/login.component.css']
})
export class SetupScreensComponent implements OnInit {

  formNo: number[] = [];
  count: number;
  userForm: FormGroup;


  constructor(private setupService: SetupScreensService, 
              private formBuilder: FormBuilder, 
              private loggedInUser: LoginService) {
                this.buildSetupForm();
  }

  ngOnInit() {
    if(this.loggedInUser.getUserId() != -1) 
      this.setupService.getInfoById(this.loggedInUser.getUserId())
      .subscribe((data : UserJob) => {        
        this.userForm.get('fullname').setValue(data.fullname)
        this.userForm.get('email').setValue(data.email)
        this.userForm.get('mobile').setValue(data.mobile)
        this.userForm.get('address').setValue(data.address)
        this.userForm.get('title').setValue(data.title)
        this.userForm.get('from').setValue(data.from)
        this.userForm.get('to').setValue(data.to)
        this.userForm.get('jobDesc').setValue(data.jobDesc)
      })
    else
      this.buildSetupForm()
  }

  buildSetupForm() {
    this.userForm = this.formBuilder.group({
      fullname: new FormControl(null),
      email: new FormControl(null),
      mobile: new FormControl(null),
      address: new FormControl(null),
      title: new FormControl(null),
      from: new FormControl(null),
      to: new FormControl(null),
      jobDesc: new FormControl(null)
    });
  }


  onUpdatet(formValues) {
    let id = 0;
    this.setupService.updateJobDesc(formValues, id);
  }

  onSubmit() {
    // Convert object into text
    var userInfoText = this.userForm.value as UserJob;
    this.setupService.saveJobDesc(userInfoText).subscribe(data => { 
      ErrorShowUtil.popupError('Success', 'Your information is submitted correctly', 'OK', 'success');
    },(error : ErrorHandler) => {
      ErrorShowUtil.popupError('Success', 'error.error.message', 'OK', 'error');
    });
  }

  addNewForm() {
    debugger
    this.formNo.push(this.count++)
  }

  reset() {
    debugger
    this.userForm.reset(null);
  }
}
