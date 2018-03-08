import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router, Params } from '@angular/router';
import {UsersService} from '../../services/users.service';
import {NgForm} from "@angular/forms";


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
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})



export class UserComponent implements OnInit {

  constructor(private route: ActivatedRoute, private usersService: UsersService) { }

  user: User;

  firstNameInput = '';

  ngOnInit() {
    this.user = new User();
    this.user.id = +this.route.snapshot.params['id'];
    this.loadUser(this.user.id);

    this.route.params.subscribe((params: Params ) => {
      this.user.id = +params ['id'];
      this.loadUser(this.user.id);
    });
}

  loadUser(id: number ) {
    this.usersService
      .getUser(id)
      .subscribe((user: User ) => {
          this.user = user;
        },
        (error) => {
          alert(error);
        });
  }

  submitForm(form: NgForm) {
    console.log(form);

  }
}
