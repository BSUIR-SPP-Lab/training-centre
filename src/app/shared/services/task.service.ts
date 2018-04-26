import { Http, Response} from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {Task} from "../models/task.model";
import {TaskWithoutInfo} from "../models/taskWithoutInfo.model";


@Injectable()
export  class TaskService extends BaseApi {


  constructor(public  http: Http) {
    super(http);
  }

  getTaskForGroup(groudID: number): Observable<Task[]> {
    return this.get(`task/byGroup/${groudID}`);
  }

  addTask(task: TaskWithoutInfo): Promise<any> {
    return this.post(`task/add`, task);
  }
}
