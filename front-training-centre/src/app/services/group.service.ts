import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

interface Group {
  coordinatorId: number;
  courseId: number;
  groupId: number;
}

@Injectable()

export class GroupService {

  constructor (private http: Http) {}

  getGroups() {
    // console.log('get func');
    return this.http.get('http://localhost:8080/group/all')
      .map((response: Response ) =>  response.json())
      .catch((error: Response ) => {
        return Observable.throw('Problems when loading group');
      });
  }

  addGroup(group: Group): Promise<any> {
    // console.log(task);
    return this.http.post('http://localhost:8080/group/add', group )
      .toPromise()
      .then((response: Response) => response);
  }

  getGroup(id: number) {
    return this.http.get(`http://localhost:8080/group/${id}`)
      .map((response: Response) =>  response.json())
      .catch((error: Response) => {
        return Observable.throw('Problems when loading group');
      });
  }

  deleteGroup(id: number): Promise<any> {
    return this.http.delete(`http://localhost:8080/group/delete/${id}`)
      .toPromise()
      .then((response: Response) => response);
  }

  updateGroup (group: Group): Promise<any>  {
    return this.http.post(`http://localhost:8080/group/update/${group.groupId}`, group )
      .toPromise()
      .then((response: Response) => response);
  }

}
