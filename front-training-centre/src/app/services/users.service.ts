import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

interface User {
    email: string;
    firstName: string;
    id: number;
    lastName: string;
    login: string;
    password: string;
    phone: string;
    role: string;
  }

@Injectable()
export class UsersService {
    constructor(private http: Http ) {}

    getUsers() {
        return this.http.get('http://localhost:8080/user/all')
            .map((response: Response ) =>  response.json())
            .catch((error: Response ) => {
                return Observable.throw('Problem');
            });
    }

    addUser(user: User){
        return this.http.post('http://localhost:8080/user/add', user )
        .map((response: Response ) =>  response);
    }

    getUser(id: number) {
      return this.http.get(`http://localhost:8080/user/${id}`)
        .map((response: Response) =>  response.json())
        .catch((error: Response) => {
          return Observable.throw('Problem');
        });
    }
}
