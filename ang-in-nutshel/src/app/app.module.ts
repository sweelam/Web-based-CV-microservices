import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { SweetAlert2Module } from '@toverux/ngx-sweetalert2';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { APP_IMPORTS } from './app-constants/app.imports'
import { APP_PROVIDERS } from './app-constants/app.providers';
import { SetupScreensComponent } from './setup-screens/setup-screens.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { LoginComponent } from './login/login.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppInterceptorService } from './interceptors/app-interceptor.service';
import { WorkExperienceComponent } from './work-experience/work-experience.component';
import { EducationComponent } from './education/education.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SetupScreensComponent,
    WelcomeComponent,
    LoginComponent,
    WorkExperienceComponent,
    EducationComponent
  ],
  imports: [
    SweetAlert2Module.forRoot(),
    BrowserModule,
    APP_IMPORTS,
    ReactiveFormsModule
  ],
  providers: [
    APP_PROVIDERS,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AppInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
