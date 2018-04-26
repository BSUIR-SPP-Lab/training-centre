import { Http, Response } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {HttpClient, HttpResponse} from "@angular/common/http";

@Injectable()
export class BaseApi {
  private baseUrl = 'http://localhost:8181/';

  constructor(public http: Http) {
  }

  private getUrl(url: string = ''): string {
    return this.baseUrl + url;
  }

  public get(url: string = ''): Observable<any> {
    return this.http.get(this.getUrl(url))
      .map((response: Response ) => response.json())
        .catch((error: Response) => {
            return Observable.throw('Problems with get method');
    });
  }



  public post(url: string = '', data: any = {}): Promise<any> {

    return this.http.post(this.getUrl(url), data)
      .toPromise()
      .then((resonse) => resonse);
  }

  public post2(url: string = '', data: any = {}): Promise<any> {
    return this.http.post(this.getUrl(url), data)
      .toPromise();
  }


  public put(url: string = '', data: any = {}): Observable<any> {
    return this.http.put(this.getUrl(url), data)
      .map((response: Response) => response.json());
  }

  public getDocumentServerRoute(): string {
    return this.baseUrl ;
  }

  errorHandler(error: any): void {
    console.log(error);
  }
}

// getTasks() {
//   // console.log('get func');
//   return this.http.get('http://localhost:8080/task/all')
//     .map((response: Response ) => response.json())
//     .catch((error: Response ) => {
//       return Observable.throw('Problems when loading task');
//     });
// }
//
// addTask(task: Task): Promise<any> {
//   // console.log(task);
//   return this.http.post('http://localhost:8080/task/add', task )
//     .toPromise()
//     .then((response: Response) => response);
// }
//
// getTask(id: number) {
//   return this.http.get(`http://localhost:8080/task/${id}`)
//     .map((response: Response) =>  response.json())
//     .catch((error: Response) => {
//       return Observable.throw('Problems when loading task');
//     });
// }
//
// deleteTask(id: number): Promise<any> {
//   return this.http.delete(`http://localhost:8080/task/delete/${id}`)
//     .toPromise()
//     .then((response: Response) => response);
// }
//
// updateTask (task: Task): Promise<any>  {
//   return this.http.post(`http://localhost:8080/task/update/${task.taskInfoId}`, task )
//     .toPromise()
//     .then((response: Response) => response);
// }
