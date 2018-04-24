import { Http, Response} from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {User} from "../models/user.model";

@Injectable()
export  class UsersService extends BaseApi {

  users: User[] = [
    {
      email: 'test1@gmail.com', firstName: 'firstname1',
      lastName: 'lastname1', login: 'login1', password: 'pass', phone: 'phone',
      role: 'USER', id: 1
    },
    {
      email: 'test2@gmail.com', firstName: 'firstname2',
      lastName: 'lastname2', login: 'login1', password: 'pass', phone: 'phone',
      role: 'ADMIN', id: 2
    }
  ];

  roles = [
    {
      name: 'ADMIN', id: 1
    },
    {
      name: 'USER', id: 2
    },
    {
      name: 'COORD', id: 3
    },
    {
      name: 'TEAC', id: 4
    },
  ];

  constructor(public  http: Http) {
    super(http);
  }

  getUserByLogin(login: string): Observable<User> {
    return this.get(`/user/byLogin/${login}`)
      .catch((error: any) =>  null );
  }

  createNewUser(user: User): Observable<User> {
    return this.post('user/add', user);
  }

  getUsers() {
    return this.users;
  }

  getRoleList() {

  }
}
