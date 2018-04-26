import { Http, Response} from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {Application} from "../models/application.model";

@Injectable()
export  class ApplicationService extends BaseApi {

  constructor(public  http: Http) {
    super(http);
  }

  createApplication(studentId: number, courseId: number): Promise<any> {
    return this.post('application/add', new Application(courseId, studentId));
  }

}
