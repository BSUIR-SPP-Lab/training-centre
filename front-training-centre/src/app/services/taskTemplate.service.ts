import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

interface TaskTemplate {
  body: string;
  name: string;
  taskInfoId: number;
}

@Injectable()

export class TaskTemplateService {

  constructor (private http: Http) {}

  getTaskTemplates() {
    // console.log('get func');
    return this.http.get('http://localhost:8080/taskInfo/all')
      .map((response: Response ) =>  response.json())
      .catch((error: Response ) => {
        return Observable.throw('Problems when loading task templates');
      });
  }

  addTaskTemplate(task: TaskTemplate): Promise<any> {
    // console.log(task);
    return this.http.post('http://localhost:8080/taskInfo/add', task )
      .toPromise()
      .then((response: Response) => response);
  }

  getTaskTemplate(id: number) {
    return this.http.get(`http://localhost:8080/taskInfo/${id}`)
      .map((response: Response) =>  response.json())
      .catch((error: Response) => {
        return Observable.throw('Problems when loading task templates');
      });
  }

  deleteTaskTemplate(id: number): Promise<any> {
    return this.http.delete(`http://localhost:8080/taskInfo/delete/${id}`)
      .toPromise()
      .then((response: Response) => response);
  }

  updateTaskTemplate (task: TaskTemplate): Promise<any>  {
    return this.http.post(`http://localhost:8080/taskInfo/update/${task.taskInfoId}`, task )
      .toPromise()
      .then((response: Response) => response);
  }

}
