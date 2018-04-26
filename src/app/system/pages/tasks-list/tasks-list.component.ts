import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {CourseService} from "../../../shared/services/course.service";
import {AuthService} from "../../../shared/services/auth.service";
import {GroupService} from "../../../shared/services/group.service";
import {Subscription} from "rxjs/Subscription";
import {TaskService} from "../../../shared/services/task.service";
import {Task} from "../../../shared/models/task.model";

@Component({
  selector: 'tc-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.scss']
})
export class TasksListComponent implements OnInit, OnDestroy {

  sub1: Subscription;
  isLoaded = false;
  tasks: Task[] = [];
  groupID: number;

  constructor(private route: ActivatedRoute,
              private courseService: CourseService,
              private authService: AuthService,
              private groupService: GroupService,
              private taskService: TaskService) { }

  ngOnInit() {
    this.sub1 = this.route.params
      .mergeMap((params: Params) => this.taskService.getTaskForGroup(params['id']))
      .subscribe((tasks: Task[]) => {
        this.tasks = tasks;
        this.groupID = this.route.snapshot.params['id'];
        this.isLoaded = true;
      });
  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

}
