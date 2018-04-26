import { Http, Response} from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {Sertificate} from "../models/sertificate.model";


@Injectable()
export  class SertificateService extends BaseApi {

  constructor(public  http: Http) {
    super(http);
  }


  getSertificateForUserId(id: number): Observable<Sertificate[]> {
    return this.get(`certificate/get/${id}`);
  }

}
