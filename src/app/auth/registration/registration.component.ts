import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UsersService} from "../../shared/services/users.service";
import {User} from "../../shared/models/user.model";

@Component({
  selector: 'tc-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  form: FormGroup;

  constructor(
    private userService: UsersService,
    private router: Router
  ) { }

  ngOnInit() {

    this.form = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email], this.checkEmails.bind(this)),
      'password': new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'name': new FormControl(null, [Validators.required]),
      'firstName': new FormControl(null, [Validators.required]),
      'phone': new FormControl(null, [Validators.required]),
      'agree': new FormControl(false, [Validators.required, Validators.requiredTrue])
    });
  }

  onSubmit() {
    const {email, firstName, name, password, phone} = this.form.value;
    const user = new User(email, firstName, name, 'login', password, phone);

    this.userService.createNewUser(user)
      .subscribe(() => {
        this.router.navigate(['/login'], {
          queryParams: {
            nowCanLoggin: true
          }
        });
      });
  }

  checkEmails(control: FormControl): Promise<any> {
    //TODO this
    // return new Promise((resolve, reject) => {
    //   this.userService.getUserByEmail(control.value)
    //     .subscribe((user: User) => {
    //       if (user) {
    //         resolve({forbiddenEmail: true});
    //       } else {
    //         resolve(null);
    //       }
    //     });
    // })
  }

}
