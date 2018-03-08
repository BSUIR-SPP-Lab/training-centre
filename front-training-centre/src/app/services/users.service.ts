import { Injectable } from "@angular/core";
import { Http, Response } from '@angular/http';

interface User {
    email: string;
    firstName:string;
    id: number;
    lastName: string;
    login: string;
    password: string;
    phone: string;
    role: string;
  }

@Injectable()
export class UsersService {
    constructor(private http:Http){}

    getUsers() {
        return this.http.get('http://localhost:8080/user/all')
            .map((response:Response) =>  response.json());
    }

    addUser(user: User){
        return this.http.post('http://localhost:8080/user/add',user)
        .map((response:Response) =>  response.json());
    }
}