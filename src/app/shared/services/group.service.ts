import {Http, Response} from '@angular/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {Group} from "../models/group.model";


@Injectable()
export class GroupService extends BaseApi {

  constructor(public  http: Http) {
    super(http);
  }

  getGroupNumberByCourseAndUserID(studentID: number, courseID: number): Observable<number> {
    return this.get(`group/groupId/${courseID}/${studentID}`);
  }

  addGroup(group: Group): Promise<any> {
    return this.post('group/add', group);
  }

  getGroups(): Observable<Group[]> {
    return this.get('group/all');
  }
}

