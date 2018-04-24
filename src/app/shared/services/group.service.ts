import {Http, Response} from '@angular/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';


@Injectable()
export class GroupService extends BaseApi {

  constructor(public  http: Http) {
    super(http);
  }

  getGroupNumberByCourseAndUserID(studentID: number, courseID: number): Observable<number> {
    return this.get(`/group/groupId/${courseID}/${studentID}`);
  }

}

