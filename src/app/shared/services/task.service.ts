import { Http, Response} from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {Task} from "../models/task.model";


@Injectable()
export  class TaskService extends BaseApi {

  taskStub: Task[] = [
    {name: 'taskname1', body: 'taskbsdfsfdsfdsfdody1',
      firstName: 'firstName' , lastName: 'lastName', uploadTime: '2018-04-19T08:48:20.601Z', taskId: 1},
    {name: 'taskname21', body: 'tasksbsdfsdfsdfsdfsdfsdfody1',
      firstName: 'firstName' , lastName: 'lastName', uploadTime: '2018-04-19T08:48:20.601Z', taskId: 12 },
    {name: 'taskname31', body: 'tasksfdsdfsfdsdfsdffffffbody1',
      firstName: 'firstName' , lastName: 'lastName', uploadTime: '2018-04-19T08:48:20.601Z', taskId: 13 },
    {name: 'taskname341', body: 'tasksdffffffffffffffffsfdbody1',
      firstName: 'firstName' , lastName: 'lastName', uploadTime: '2018-04-19T08:48:20.601Z', taskId: 14 },
    {name: 'taskname41', body: 'tasksdfffffffffffbody1',
      firstName: 'firstName' , lastName: 'lastName', uploadTime: '2018-04-19T08:48:20.601Z', taskId: 15 }
  ];

  constructor(public  http: Http) {
    super(http);
  }


  getTasksForGroup(groupID: number) {
    return this.taskStub;
  }

  // getTaskForGroup(groudID: number): Observable<Task[]> {
  //   return this.get(`${groudID}`);
  // }

}
