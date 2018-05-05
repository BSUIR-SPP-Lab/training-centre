import { Component, OnInit } from '@angular/core';
import {UsersService} from "../../../shared/services/users.service";
import {User} from "../../../shared/models/user.model";
import {NgForm} from "@angular/forms";
import {Message} from "../../../shared/models/message.model";
import {Subscription} from "rxjs/Subscription";
import {Role} from "../../../shared/models/role.model";

@Component({
  selector: 'tc-role-controller',
  templateUrl: './role-controller.component.html',
  styleUrls: ['./role-controller.component.scss']
})
export class RoleControllerComponent implements OnInit {

  users: User[] = [];
  currentUser: User;
  currentUserId: number;
  currentRole: string;
  message: Message;
  sub1: Subscription;
  isLoaded = false;
  roles: Role[] = [];

  constructor(private userService: UsersService) { }

  ngOnInit() {
    this.message = new Message('success', '');
    this.sub1 = this.userService.getUsers()
      .subscribe( (users: User[]) => {
        this.users = users;
        this.isLoaded = true;
      });
    this.roles = this.userService.getRoleList();
  }

  onCategoryChange() {
    this.currentUser = this.users
      .find(c => c.id === +this.currentUserId);
  }

  onSubmit(form: NgForm) {
    console.log(form.value);
    const {user, role} = form.value;
    this.userService.changeRole(user, role)
      .then((res) => {
        this.showMessage({
          text: 'Роль пользователя изменена успешно!',
          type: 'info'});
      })
      .catch(reason => {
        this.showMessage({
          text: 'Невозможно изменить роль пользователя!',
          type: 'warning'});
      });
  }

  private showMessage(message: Message) {
    this.message = message;
    window.setTimeout( () => {
      this.message.text = '';
    }, 5000);
  }
}
