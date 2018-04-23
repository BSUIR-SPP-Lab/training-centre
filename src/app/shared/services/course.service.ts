import { Http, Response} from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {User} from "../models/user.model";
import {Course} from "../models/course.model";

@Injectable()
export  class CourseService extends BaseApi {

  constructor(public  http: Http) {
    super(http);
  }

  getCourses(): Observable<Course[]> {
       return this.get('/course/get/coursesWithInfo');
  }

  getCourseForID(id: number): Observable<Course[]>  {
    return this.get(`/course/get/coursesWithInfoByUserId/${id}`);
  }

}
