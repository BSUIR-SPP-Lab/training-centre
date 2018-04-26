import { Http, Response} from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {CourseInfo} from "../models/courseInfo.model";


@Injectable()
export  class CourseInfoService extends BaseApi {

  constructor(public  http: Http) {
    super(http);
  }

  getCourses(): Observable<CourseInfo[]> {
    return this.get('courseInfo/all');
  }

  addCourse(course: CourseInfo): Promise<any> {
    return this.post(`courseInfo/add`, course);
  }


}
