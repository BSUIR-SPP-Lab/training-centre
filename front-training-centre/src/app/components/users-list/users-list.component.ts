import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../services/users.service';


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

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  users: User[] = [];

  constructor(private usersService: UsersService) { }

  ngOnInit() {
    
  }
  
  loadUsers(){
    this.usersService
    .getUsers().
    subscribe((users:User[]) => {
      this.users = users;
    })
  }

}
