import { Http, Response} from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {BaseApi} from '../core/base-api';
import {User} from "../models/user.model";
import {TeacherGroup} from "../models/teacher.model";
import {Role} from "../models/role.model";

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

  role: Role[] = [
    {role: 'USER', text: 'Пользователь'},
    {role: 'STUDENT', text: 'Студент'},
    {role: 'TEACHER', text: 'Преподаватель'},
    {role: 'COORDINATOR', text: 'Координатор'},
    {role: 'ADMIN', text: 'Администратор'}
  ];

  constructor(public  http: Http) {
    super(http);
  }

  getUserByLogin(login: string): Observable<User> {
    return this.get(`/user/byLogin/${login}`)
      .catch((error: any) =>  null );
  }

  checkUser(login: string, password): Promise<User> {
    const data =  {login: login, password: password};
    console.log('data: ', data);
    return this.post2(`user/login/`, data);
  }

  createNewUser(user: User): Promise<User> {
    return this.post('user/add', user);
  }

  getUserByRole(role: string): Observable<User[]> {
    return this.get(`user/findByRole/${role}`);
  }

  getUsers(): Observable<User[]> {
    return this.get('user/all');
  }

  addTeacher(userID: number, groupID: number): Promise<any> {
    return this.post('teacher/add', { groupId: groupID, teacherId: userID});
  }

  updateUser(user: User): Promise<any> {
    return this.post(`user/update/${user.id}`, user);
  }

  getUserById(userId: number): Observable<User> {
    return this.get(`user/${userId}`);
  }

  getRoleList(): Role[] {
    return this.role;
  }

  getTeacherGroup(): Observable<TeacherGroup[]> {
    return this.get('teacher/get');
  }

  changeRole(id: number, role: string): Promise<any> {
    return this.post(`user/updateRole/${id}`, role);
  }

}
