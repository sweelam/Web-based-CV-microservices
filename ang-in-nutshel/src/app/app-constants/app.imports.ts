import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule , FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule , Routes } from '@angular/router';
import { routing } from '../app.routing';

export const APP_IMPORTS = [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    routing
];