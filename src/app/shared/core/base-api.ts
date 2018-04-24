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
      .map((response: Response) => response.json());
  }

  public post(url: string = '', data: any = {}): Observable<any> {

    return this.http.post(this.getUrl(url), data);
      // .map((response: Response) => response.json());

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
