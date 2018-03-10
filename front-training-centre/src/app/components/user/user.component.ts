import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router, Params } from '@angular/router';
import {UsersService} from '../../services/users.service';
import {NgForm} from '@angular/forms';


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

  constructor(private route: ActivatedRoute, private usersService: UsersService, private router: Router) { }

  user: User;

  isChanged: boolean;
  isCreated: boolean;

  ngOnInit() {
    this.user = new User();
    this.user.id = this.route.snapshot.params['id'];
    if ((this.route.snapshot.params['id'] !== undefined) && (this.route.snapshot.params['id'] !== 'add')) {
      this.isChanged = false;
      this.isCreated = true;
      console.log(this.route.snapshot.params['id']);
      this.user.id = +this.route.snapshot.params['id'];
      this.loadUser(this.user.id);
    } else {
      this.isChanged = true;
      this.isCreated = false;
      this.user = new User();
    }

    this.route.params.subscribe((params: Params ) => {
      if ((this.route.snapshot.params['id'] !== undefined) && (this.route.snapshot.params['id'] !== 'add')) {
        console.log(this.route.snapshot.params['id']);
        this.isChanged = true;
        this.isCreated = false;
        this.user.id = +this.route.snapshot.params['id'];
        this.loadUser(this.user.id);
      } else {
          this.isChanged = false;
          this.isCreated = true;
          this.user = new User();
      }
    });
}

  loadUser(id: number ) {
    this.usersService
      .getUser(id)
      .subscribe((user: User ) => {
          this.user = user;
        },
        (error) => {
          alert('Error loading user');
        });
  }

  deleteUser() {
    this.usersService
      .deleteUser(this.user.id)
      .then(() => {
        console.log('User was deleted');
        this.router.navigate(['/users']);
      },
        (error) => {
        alert('Error while deleting user');
        });

  }


  submitForm(form: NgForm) {
    // console.log(form);
    if (this.isCreated) {
      // console.log('isCreated');
      this.usersService.addUser(this.user)
      .then(_ => {
        // console.log('User was added');
        this.user = new User();
        this.router.navigate(['/users']);

      });

    } else {
      if (this.isChanged) {
        // console.log('isChanged');
        this.usersService.updateUser(this.user)
          .then(_ => {
            // console.log('User was changed');
            this.router.navigate(['/users']);
          });
      }
    }

  }
}
