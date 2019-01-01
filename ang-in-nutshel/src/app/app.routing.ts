import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from '../app/home/home.component';
import { SetupScreensComponent } from './setup-screens/setup-screens.component';
import { WelcomeComponent } from '../app/welcome/welcome.component';
import { LoginComponent } from './login/login.component'
import { UserGuard } from '../app/login/user.guard';

// Order is matter
const appRouts: Routes = [
    {
        path: 'home/:accId',
        component: HomeComponent,
        canActivate: [UserGuard]
    },
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [UserGuard]
    },
    {
        path: 'setup/:accId',
        component: SetupScreensComponent,
        canActivate: [UserGuard]
    },
    { path: 'login', component: LoginComponent },
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'login'
    },
    {
        path: '**',
        pathMatch: 'full',
        component: WelcomeComponent
    }
];

export const routing = RouterModule.forRoot(appRouts);