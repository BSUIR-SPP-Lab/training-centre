import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UsersService} from "../../shared/services/users.service";
import {AuthService} from "../../shared/services/auth.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Message} from "../../shared/models/message.model";
import {fadeStateTrigger} from "../../shared/animations/fade.animation";
import {User} from "../../shared/models/user.model";


@Component({
  selector: 'tc-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [fadeStateTrigger]
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  message: Message;
  constructor(
    private usersService: UsersService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) { }


  ngOnInit() {
    this.message = new Message('danger', '');

    this.route.queryParams
      .subscribe((params: Params) => {
        if (params['nowCanLoggin']) {
          this.showMessage({
            text: 'теперь вы можете войти в систему',
            type: 'success'});
        } else {
          if (params['accessDenied']) {
            this.showMessage({
              text: 'Войдите в систему!',
              type: 'warning'});
          } else {
            if (params['accessDeniedEnroll']) {
              this.showMessage({
                text: 'Для записи на курс Вам необходимо войти в систему',
                type: 'info'});
            }
          }
        }
      });

    this.form = new FormGroup({
      'login': new FormControl(null, [Validators.required]),
      'password': new FormControl(null, [Validators.required, Validators.minLength(6)]),
    });
  }

  private showMessage(message: Message) {
    this.message = message;
    window.setTimeout( () => {
      this.message.text = '';
    }, 5000);
  }

  onSubmit() {
    const formData = this.form.value;
    console.log(formData);
    console.log('This is stub.');
    // this.usersService.getUserByLogin(formData.login)
    //   .subscribe( (user: User) => {
    //     console.log(user);
    //     if (user) {
    //       if (user.password === formData.password) {
    //         this.message.text = '';
    //         this.authService.login();
    //         window.localStorage.setItem('user', JSON.stringify(user));
    //         this.router.navigate(['/system', 'start-page']);
    //       } else {
    //         this.showMessage( {
    //           text: 'пароль не верный!',
    //           type: 'danger'});
    //       }
    //     } else {
    //       this.showMessage( {
    //         text: 'такого пользователя нет!',
    //         type: 'danger'});
    //     }
    //   });
  }

}
