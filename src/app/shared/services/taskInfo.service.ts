import { Http, Response} from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {CourseInfo} from "../models/courseInfo.model";
import {TaskInfo} from "../models/taskInfo.model";


@Injectable()
export  class TaskInfoService extends BaseApi {

  constructor(public  http: Http) {
    super(http);
  }


  getTasks(): Observable<TaskInfo[]> {
    return this.get('taskInfo/all');
  }

  addTask(task: TaskInfo): Promise<any> {
    return this.post(`taskInfo/add`, task);
  }

}
