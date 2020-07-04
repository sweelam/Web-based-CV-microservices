import {Injectable} from '@angular/core'
import { Location } from '@angular/common';



@Injectable()
export class AppService {
    constructor (private location: Location){}

  back() {
    this.location.back();
  }
}