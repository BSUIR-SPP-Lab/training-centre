import {Component, OnInit, AfterViewInit, AfterContentChecked, OnChanges} from '@angular/core';
import { UsersService } from '../../services/users.service';
import {ActivatedRoute, Params, Router, RouterEvent} from '@angular/router';


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
  user: User;

  constructor(private usersService: UsersService, private router: Router) {
    router.events.filter(event => event instanceof RouterEvent).subscribe(event => {
      if (event['url'] === '/users' ) {
        this.loadUsers();
        console.log(event['url'], 'update url');
      }
    });

  }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.user = new User();
    this.loadUsers();

  }




  loadUsers() {
    this.usersService
    .getUsers().
    subscribe((users: User[]) => {
      this.users = users;
    },
    (error) => {
      alert(error);
    }
  );
  }



}
