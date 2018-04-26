import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UsersService} from "../../shared/services/users.service";
import {User} from "../../shared/models/user.model";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'tc-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class RegistrationComponent implements OnInit, OnDestroy {

  form: FormGroup;
  sub1: Subscription;
  sub2: Subscription;

  constructor(
    private userService: UsersService,
    private router: Router
  ) { }

  ngOnInit() {

    this.form = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'login': new FormControl(null, [Validators.required], this.checkLogin.bind(this)),
      'password': new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'name': new FormControl(null, [Validators.required]),
      'firstName': new FormControl(null, [Validators.required]),
      'phone': new FormControl(null, [Validators.required]),
      'agree': new FormControl(false, [Validators.required, Validators.requiredTrue])
    });
  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

  onSubmit() {
    const {email, firstName, name, password, phone, login} = this.form.value;
    const user = new User(email, firstName, name, login, password, phone, 'USER');
    console.log(user);
    this.userService.createNewUser(user)
      .then( () => {
        this.sub1 = this.userService.getUserByLogin(login)
          .subscribe( (userI: User) => {
            userI.role = 'STUDENT';
            console.log(userI);
            this.userService.updateUser(userI)
              .then(() => {
                this.router.navigate(['/login'], {
                  queryParams: {
                    nowCanLoggin: true
                  }
                });
              });
          });
      })
      .catch(reason => {
        console.log(reason);
      });
  }

  checkLogin(control: FormControl): Promise<any> {
    return new Promise((resolve, reject) => {
      this.userService.getUserByLogin(control.value)
        .subscribe(
          (user) => {
            if (user) {
              resolve({forbiddenLogin: true});
            } else {
              resolve(null);
            }
          }
        );
    });
  }


}
