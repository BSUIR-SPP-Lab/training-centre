import {Http, Response} from '@angular/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {Solution} from "../models/solution.model";


@Injectable()
export class SolutionService extends BaseApi {

  constructor(public  http: Http) {
    super(http);
  }

  sendSolution(solution: Solution): Promise<any> {
    return this.post(`solution/add`, solution);
  }

}

