import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, FormControl } from "@angular/forms";

import { SetupScreensService } from "./setup-screens.service";
import { UserJob } from "./user";
import { ErrorHandler } from "../error-handler/error-handler";
import { SwalShowUtil } from "../error-handler/error-show-util";
import { Router } from "@angular/router";
import { AppService } from "../app-common-service";
import { LoginService } from "../login/login.service";

@Component({
  selector: "app-setup-screens",
  templateUrl: "./setup-screens.component.html",
  styleUrls: ["../login/login.component.css", "./setup-screens.component.css"],
})
export class SetupScreensComponent implements OnInit {
  formNo: number[] = [];
  count: number;
  userForm: FormGroup;
  userId: number;

  constructor(
    private setupService: SetupScreensService,
    private formBuilder: FormBuilder,
    private route: Router,
    public loginService: LoginService,
    public appService: AppService
  ) {}

  ngOnInit() {
    if (sessionStorage.getItem("userId")) {
      this.userId = +sessionStorage.getItem("userId");
      this.setupService.getInfoById(this.userId).subscribe((data: UserJob) => {
        this.userForm.get("fullName").setValue(data.fullName);
        this.userForm.get("email").setValue(data.email);
        this.userForm.get("mobile").setValue(data.mobile);
        this.userForm.get("address").setValue(data.address);
        this.userForm.get("title").setValue(data.title);
        this.userForm.get("from").setValue(data.from);
        this.userForm.get("to").setValue(data.to);
        this.userForm.get("jobDesc").setValue(data.jobDesc);
      });
    }

    this.buildSetupForm();
  }

  buildSetupForm() {
    this.userForm = this.formBuilder.group({
      fullName: new FormControl(null),
      email: new FormControl(null),
      mobile: new FormControl(null),
      address: new FormControl(null),
      title: new FormControl(null),
      from: new FormControl(null),
      to: new FormControl(null),
      jobDesc: new FormControl(null),
    });
  }

  onUpdate(formValues) {
    let id = 0;
    this.setupService.updateJobDesc(formValues, id);
  }

  onSubmit() {
    // Convert object into text
    var userInfoText = this.userForm.value as UserJob;
    this.setupService.saveJobDesc(userInfoText).subscribe(
      (data: any) => {
        if (data.status == 201) {
          SwalShowUtil.popupError(
            "Success",
            "Your information is submitted correctly",
            "OK",
            "success"
          );
          this.route.navigate(["/home", this.userId]);
        }
      },
      (error: ErrorHandler) => {
        if (error.status == 304)
          SwalShowUtil.popupError(
            "Warning",
            "Your information is not submitted correctly",
            "OK",
            "warning"
          );
        else
          SwalShowUtil.popupError(
            "Error",
            "error.error.message",
            "OK",
            "error"
          );
      }
    );
  }

  addNewForm() {
    this.formNo.push(this.count++);
  }

  reset() {
    this.userForm.reset(null);
  }
}
