import { Component, OnInit } from '@angular/core';
import {UsersService} from "../../../shared/services/users.service";
import {User} from "../../../shared/models/user.model";
import {NgForm} from "@angular/forms";
import {Message} from "../../../shared/models/message.model";

@Component({
  selector: 'tc-role-controller',
  templateUrl: './role-controller.component.html',
  styleUrls: ['./role-controller.component.scss']
})
export class RoleControllerComponent implements OnInit {

  users: User[] = [];
  currentUser: User;
  currentUserId: number;
  message: Message;
  constructor(private userService: UsersService) { }

  ngOnInit() {
    this.message = new Message('success', '');
    // //TODO sub and destr
    // this.users = this.userService.getUsers();
    // //TODO check count
    // this.currentUser = this.users[0];
    // this.currentUserId = this.currentUser.id;
  }

  onCategoryChange() {
    this.currentUser = this.users
      .find(c => c.id === +this.currentUserId);
  }

  onSubmit(form: NgForm) {
    console.log('submit', this.currentUser);
    //TODO logic
  }
}
