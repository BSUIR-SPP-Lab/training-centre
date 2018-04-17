import { Component, OnInit } from '@angular/core';
import {CourseService} from "../../shared/services/course.service";
import {Course} from "../../shared/models/course.model";
import {AuthService} from "../../shared/services/auth.service";
import {Router} from "@angular/router";
import {fadeStateTrigger} from "../../shared/animations/fade.animation";
import {Message} from "../../shared/models/message.model";

@Component({
  selector: 'tc-star-page',
  templateUrl: './start-page.component.html',
  styleUrls: ['./start-page.component.scss'],
  animations: [fadeStateTrigger]
})
export class StarPageComponent implements OnInit {

  courses: Course[] = [];

  isLoaded = false;
  message: Message;

  constructor(private  courseServer: CourseService,
              private  authService: AuthService,
              private router: Router) {
    //TODO: do observable, subs and unsub
    this.courses = courseServer.getCourse();
    this.isLoaded = true;
  }

  ngOnInit() {
    this.showMessage({
      text: 'Добро Пожаловать на сайт',
      type: 'info'});
  }

  private showMessage(message: Message) {
    this.message = message;
    window.setTimeout( () => {
      this.message.text = '';
    }, 5000);
  }

  onEnrollEvent(curseID) {
    console.log(curseID);
    if (this.authService.isLoggedIn()) {
      console.log("log");
    } else {
      console.log("unlog");
      this.router.navigate(['/login'], {
        queryParams: {
          accessDeniedEnroll: true
        }
      });
    }
  }
}
