import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

interface Task {
  groupId: number;
  taskId: number;
  taskInfoId: number;
  teacherId: number;
  uploadTime: string;
}

@Injectable()

export class TaskService {

  constructor (private http: Http) {}

  getTasks() {
    // console.log('get func');
    return this.http.get('http://localhost:8080/task/all')
      .map((response: Response ) =>  response.json())
      .catch((error: Response ) => {
        return Observable.throw('Problems when loading task');
      });
  }

  addTask(task: Task): Promise<any> {
    // console.log(task);
    return this.http.post('http://localhost:8080/task/add', task )
      .toPromise()
      .then((response: Response) => response);
  }

  getTask(id: number) {
    return this.http.get(`http://localhost:8080/task/${id}`)
      .map((response: Response) =>  response.json())
      .catch((error: Response) => {
        return Observable.throw('Problems when loading task');
      });
  }

  deleteTask(id: number): Promise<any> {
    return this.http.delete(`http://localhost:8080/task/delete/${id}`)
      .toPromise()
      .then((response: Response) => response);
  }

  updateTask (task: Task): Promise<any>  {
    return this.http.post(`http://localhost:8080/task/update/${task.taskInfoId}`, task )
      .toPromise()
      .then((response: Response) => response);
  }

}
