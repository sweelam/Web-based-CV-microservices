
import {HomeService} from '../home/home.service'
import { SetupScreensService } from '../setup-screens/setup-screens.service'
import { LoginService } from '../login/login.service';
import { UserGuard } from '../login/user.guard';
import {AppInterceptorService} from '../interceptors/app-interceptor.service';

export const APP_PROVIDERS = [HomeService, SetupScreensService, LoginService, UserGuard, AppInterceptorService];