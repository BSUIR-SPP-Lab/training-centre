import { Http, Response} from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {Sertificate} from "../models/sertificate.model";


@Injectable()
export  class SertificateService extends BaseApi {

  sertificates: Sertificate[] = [
    {sertificateId: 1, studentId: 1, courseName: 'Крутой курс',
      end: new Date( '2018-04-19T08:48:20.601Z'), start: new Date( '2018-04-19T08:48:20.601Z'), coordinatorName: 'megoCoord'},
    {sertificateId: 2, studentId: 1, courseName: 'Крутой курс',
      end: new Date( '2018-04-19T08:48:20.601Z'), start: new Date( '2018-04-19T08:48:20.601Z'), coordinatorName: 'megoCoord'},
    {sertificateId: 3, studentId: 1, courseName: 'Крутой курс',
      end: new Date( '2018-04-19T08:48:20.601Z'), start: new Date( '2018-04-19T08:48:20.601Z'), coordinatorName: 'megoCoord'},
    {sertificateId: 4, studentId: 1, courseName: 'Крутой курс',
      end: new Date( '2018-04-19T08:48:20.601Z'), start: new Date( '2018-04-19T08:48:20.601Z'), coordinatorName: 'megoCoord'}
  ];

  constructor(public  http: Http) {
    super(http);
  }

  getSertificateForId(id: number) {
    return this.sertificates;
  }



}
