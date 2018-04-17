import { Http, Response} from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {User} from "../models/user.model";
import {Course} from "../models/course.model";

@Injectable()
export  class CourseService extends BaseApi {

  courseTemp: Course[] = [
    { name: 'Первый курс', description: 'Мы будем изучать основы выживания в универе.', coordinatorId: 5, courseId: 1},
    { name: 'Второй курс', description: 'Мы будем изучать основы выживания в универе.', coordinatorId: 6, courseId: 2},
    { name: 'Какой-нибудь курс', description: 'Мы будем изучать основы выживания в универе.', coordinatorId: 7, courseId: 2},
    { name: 'ОООЧЕНЬ крутой курс', description: 'Мы будем изучать основы выживания в универе.', coordinatorId: 8, courseId: 3},
    { name: 'Агонь курс', description: 'Мы будем изучать основы выживания в универе.', coordinatorId: 8, courseId: 4},
    { name: 'Ну такой себе курс', description: 'Мы будем изучать основы выживания в универе.', coordinatorId: 9, courseId: 5}
  ];

  constructor(public  http: Http) {
    super(http);
  }


  getCourse() {
       return this.courseTemp;
  }


}
