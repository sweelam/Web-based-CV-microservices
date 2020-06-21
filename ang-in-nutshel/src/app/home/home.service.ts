import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { API } from '../app-constants/Constants';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class HomeService {
    urlAccount: string;
    file: string = "../../assets/";

    constructor(private http: HttpClient) {
        this.urlAccount = API.ROOT + '/cv-service/api-cv/account/';
    }

    home(): Observable<string> {
        return this.http.get( API.ROOT + '/cv-service/api-cv/home/all', { responseType: 'text' });
    }

    getFullName(accId) {
        // return this.http.get(this.urlAccount + 'fullName/' + accId, { responseType: 'text' });
        return Observable.of("Mohamed Sweelam");
    }

    getInfoDetails(accId) {
        // return this.http.get(this.urlAccount + 'info/details/' + accId , { responseType: 'json' });
        return this.http.get(this.file + "info.json" , { responseType: 'text' });
    }

    getJobInfo(accId) {
        // return this.http.get(this.urlAccount + 'job/info/' + accId , { responseType: 'text' });
        return this.http.get(this.file + "job.json" , { responseType: 'text' });
    }

    getUserData(): Observable<any> {
        // return this.http.get( API.ROOT + '/test/response', { responseType: 'json' });
        return this.http.get(this.file + "fullname.json" , { responseType: 'text' });
    }

    getUserSkills(accId): Observable<any> {
        // return this.http.get(this.urlAccount + 'info/skills/' + accId, { responseType: 'json' });
        return this.http.get(this.file + "skills.json" , { responseType: 'text' });
    }

}