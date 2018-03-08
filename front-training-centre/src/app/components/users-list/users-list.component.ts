import { Component, OnInit, AfterViewInit } from '@angular/core';
import { UsersService } from '../../services/users.service';


class User {
  email: string;
  firstName: string;
  id: number;
  lastName: string;
  login: string;
  password: string;
  phone: string;
  role: string;
}

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit, AfterViewInit {
  users: User[] = [];

  userName:string = '';

  user: User;

  constructor(private usersService: UsersService) { }
  ngOnInit(){}

  ngAfterViewInit() {
    this.user = new User();
    this.user.email = 'test@gmail.com';
    this.user.lastName = 'testLastName';
    this.user.password = 'testpass';
    this.user.phone = '8800553522';
    this.user.role = 'STUDENT';
    this.user.id = 0;
    this.loadUsers();
  }

  loadUsers(){
    this.usersService
    .getUsers().
    subscribe((users:User[]) => {
      this.users = users;
    },
    (error) => {
      alert(error);
    }
  );
  }

  addUser() {
    this.user.login = this.userName;
    this.user.firstName = this.userName;
    this.usersService.addUser(this.user)
    .subscribe();
    this.userName = '';
  }

}
