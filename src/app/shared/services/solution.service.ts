import {Http, Response} from '@angular/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {Solution} from "../models/solution.model";
import {SolutionInfo} from "../models/solutionInfo.model";


@Injectable()
export class SolutionService extends BaseApi {

  constructor(public  http: Http) {
    super(http);
  }

  sendSolution(solution: Solution): Promise<any> {
    return this.post(`solution/add`, solution);
  }

  getSolutionForUser(userId: number): Observable<SolutionInfo[]> {
    return this.get(`solution/byUserId/${userId}`);
  }

  getSolutionForGroup(groupId: number): Observable<SolutionInfo[]> {
    return this.get(`solution/byGroupId/${groupId}`);
  }

  updateSolution(solution: Solution): Promise<any> {
    return this.post(`solution/update/${solution.taskId}/${solution.userId}`, solution);
  }
}

