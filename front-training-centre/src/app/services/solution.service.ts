import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

interface Solution {
  taskId: number;
  userId: number;
  mark: number;
  filepath: string;
  notes: string;
  teacherNotes: string;
  uploadTime: Date;
}

@Injectable()

export class SolutionService {

  constructor (private http: Http) {}

  getSolutions() {
    return this.http.get('http://localhost:8080/solution/all')
      .map((response: Response ) =>  response.json())
      .catch((error: Response ) => {
        return Observable.throw('Problems when loading solution');
      });
  }

  addSolution(solution: Solution): Promise<any> {
    // console.log(task);
    return this.http.post('http://localhost:8080/solution/add', solution )
      .toPromise()
      .then((response: Response) => response);
  }

  getSolution(taskId: number, userId: number) {
    return this.http.get(`http://localhost:8080/solution/${taskId}/${userId}`)
      .map((response: Response) =>  response.json())
      .catch((error: Response) => {
        return Observable.throw('Problems when loading solution');
      });
  }

  deleteSolution(taskId: number, userId: number): Promise<any> {
    return this.http.delete(`http://localhost:8080/solution/delete/${taskId}/${userId}`)
      .toPromise()
      .then((response: Response) => response);
  }

  updateSolution (solution: Solution): Promise<any>  {
    return this.http.post(`http://localhost:8080/solution/update/${solution.taskId}/${solution.userId}`, solution )
      .toPromise()
      .then((response: Response) => response);
  }

}
