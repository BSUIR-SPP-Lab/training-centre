import {Component, OnDestroy, OnInit} from '@angular/core';
import {ApplicationService} from "../../../shared/services/application.service";
import {ApplicationWithInfo} from "../../../shared/models/applicationWithInfo.model";
import {Subscription} from "rxjs/Subscription";
import {Message} from "../../../shared/models/message.model";
import {Course} from "../../../shared/models/course.model";
import {Observable} from "rxjs/Observable";
import {CourseService} from "../../../shared/services/course.service";
import {User} from "../../../shared/models/user.model";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'tc-aplications',
  templateUrl: './aplications.component.html',
  styleUrls: ['./aplications.component.scss']
})
export class AplicationsComponent implements OnInit, OnDestroy {

  isLoaded = false;
  applications: ApplicationWithInfo[] = [];
  courseAplications: ApplicationWithInfo[] = [];
  courses: Course[] = [];
  course: Course;
  student: User;
  app: ApplicationWithInfo;
  sub1: Subscription;
  courseID: number;
  message: Message;

  constructor(private applicationService: ApplicationService,
              private courseService: CourseService) {
  }

  ngOnInit() {
    this.student = new User('', '', '', '', '', '', 'USER', 0);
    this.isLoaded = true;
    this.message = new Message('danger', '');
    this.sub1 = Observable.combineLatest(
      this.courseService.getCourses(),
      this.applicationService.getApplications()
    )
      .subscribe((data: [Course[], ApplicationWithInfo[]]) => {
        this.courses = data[0];
        this.course = this.courses[0];
        this.applications = data[1];
        this.isLoaded = true;
      });
  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

  onSubmit(form: NgForm) {
    const {application} = form.value;
    console.log(form.value);


    this.applicationService.approveApp(application)
      .then((res) => {
        console.log('res ', res);
        this.showMessage({
          text: 'Пользователь успешно зачислен',
          type: 'info'
        });
      })
      .catch((response => {
        console.log(response);
        this.showMessage({
          text: 'Пользователь успешно зачислен',
          type: 'info'
        });
      }));

  }

  onChangedCourse(value) {
    console.log(value);
    const {course} = value;
    const coursId = +course;
    console.log(coursId);
    console.log(this.applications);
    this.courseAplications = this.applications.filter(a => a.courseId === coursId);
    console.log(this.courseAplications);
    this.app = undefined;
  }

  onChangedUser(value) {
    console.log(value);

  }


  private showMessage(message: Message) {
    this.message = message;
    window.setTimeout(() => {
      this.message.text = '';
    }, 5000);
  }
}
