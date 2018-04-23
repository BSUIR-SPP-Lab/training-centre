import {Component, OnDestroy, OnInit} from '@angular/core';
import {CourseService} from "../../shared/services/course.service";
import {Course} from "../../shared/models/course.model";
import {AuthService} from "../../shared/services/auth.service";
import {Router} from "@angular/router";
import {fadeStateTrigger} from "../../shared/animations/fade.animation";
import {Message} from "../../shared/models/message.model";
import {Subscription} from "rxjs/Subscription";
import {ApplicationService} from "../../shared/services/application.service";

@Component({
  selector: 'tc-star-page',
  templateUrl: './start-page.component.html',
  styleUrls: ['./start-page.component.scss'],
  animations: [fadeStateTrigger]
})
export class StarPageComponent implements OnInit, OnDestroy {
  sub1: Subscription;
  sub2: Subscription;
  courses: Course[] = [];

  isLoaded = false;
  message: Message;

  constructor(private  courseServer: CourseService,
              private  authService: AuthService,
              private  applicationService: ApplicationService,
              private router: Router) {
  }

  ngOnInit() {
    this.showMessage({
      text: 'Добро Пожаловать на сайт',
      type: 'info'});
    this.sub1 = this.courseServer.getCourses()
      .subscribe((data: Course[]) => {
        this.courses = data;
        this.isLoaded = true;
      });
  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
    if (this.sub2) {
      this.sub1.unsubscribe();
    }
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
      this.sub2 = this.applicationService.createApplication(this.authService.getId(), curseID)
        .subscribe(
          (res => {
            console.log(res);
              if (res.status === 200) {
                this.showMessage({
                  text: 'Регистрация прошла успешно',
                  type: 'info'
                });
              } else {
                /// TODO this don't work
                this.showMessage({
                  text: 'Ошибка регистрации',
                  type: 'warning'
                });
              }
            }
            )
        );

    } else {
      this.router.navigate(['/login'], {
        queryParams: {
          accessDeniedEnroll: true
        }
      });
    }
  }
}
