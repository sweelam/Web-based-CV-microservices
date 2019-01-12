import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpClient, HttpParams } from '@angular/common/http';
import 'rxjs/add/operator/map';

import { UserJob } from './user';
import { API } from '../app-constants/Constants';

@Injectable()
export class SetupScreensService {
    urlAccount: string;

    constructor(private http: HttpClient) { 
        this.urlAccount = API.ROOT + '/cv-service/api-cv/account/';
    }

    updateJobDesc(form, id: number) {
        return this.http.put(this.urlAccount + "update/job/${id}", '');
    }

    saveJobDesc(formx : UserJob) {
        return this.http.post(this.urlAccount + "info/data", formx);
    }

    getInfoById(accId) {
        return this.http.get(this.urlAccount + "info/data/"+ accId);
    }
}