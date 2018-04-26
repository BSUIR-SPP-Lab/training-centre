import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../../shared/services/auth.service";
import {Subscription} from "rxjs/Subscription";
import {UsersService} from "../../../shared/services/users.service";
import {TeacherGroup} from "../../../shared/models/teacher.model";

@Component({
  selector: 'tc-solution-controller',
  templateUrl: './solution-controller.component.html',
  styleUrls: ['./solution-controller.component.scss']
})
export class SolutionControllerComponent implements OnInit, OnDestroy {
  sub1: Subscription;
  teacherGroups: TeacherGroup[];
  constructor(private authService: AuthService,
              private userService: UsersService) { }

  ngOnInit() {
    this.sub1 = this.userService.getTeacherGroup()
      .subscribe( (teacherGroups: TeacherGroup[]) => {
        const id = this.authService.getId();
        this.teacherGroups = teacherGroups.filter(g => g.teacherId === id);
        console.log(this.teacherGroups);
      });
  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

}
