import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

interface StudentGroup {
  studentId: number;
  groupId: number;
  courseComplite: boolean;
}

@Injectable()
//TODO this shit
export class StudentGroupService {

  constructor (private http: Http) {}

  getGroups() {
    // console.log('get func');
    return this.http.get('http://localhost:8080/group/all')
      .map((response: Response ) =>  response.json())
      .catch((error: Response ) => {
        return Observable.throw('Problems when loading group');
      });
  }

  addGroup(studentGroup: StudentGroup): Promise<any> {
    // console.log(task);
    return this.http.post('http://localhost:8080/group/add', studentGroup )
      .toPromise()
      .then((response: Response) => response);
  }

  updateGroup (studentGroup: StudentGroup): Promise<any>  {
    return this.http.post(`http://localhost:8080/group/update/${studentGroup.studentId}`, studentGroup )
      .toPromise()
      .then((response: Response) => response);
  }

}
