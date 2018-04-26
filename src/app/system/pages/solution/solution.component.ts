import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Data, Params} from "@angular/router";
import {CourseService} from "../../../shared/services/course.service";
import {AuthService} from "../../../shared/services/auth.service";
import {GroupService} from "../../../shared/services/group.service";
import {Subscription} from "rxjs/Subscription";
import {TaskService} from "../../../shared/services/task.service";
import {Task} from "../../../shared/models/task.model";
import {Message} from "../../../shared/models/message.model";
import {fadeStateTrigger} from "../../../shared/animations/fade.animation";
import {FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import {SolutionService} from "../../../shared/services/solution.service";
import {Solution} from "../../../shared/models/solution.model";

@Component({
  selector: 'tc-solution',
  templateUrl: './solution.component.html',
  styleUrls: ['./solution.component.scss'],
  animations: [fadeStateTrigger]
})
export class SolutionComponent implements OnInit, OnDestroy {

  sub1: Subscription;
  sub2: Subscription;
  tasks: Task[] = [];
  isLoaded = false;
  groupID: number;
  taskID: number;
  answerText: string;
  message: Message;

  constructor(private route: ActivatedRoute,
              private courseService: CourseService,
              private authService: AuthService,
              private groupService: GroupService,
              private taskService: TaskService,
              private solutionService: SolutionService) { }

  ngOnInit() {
    this.sub1 = this.route.params
      .subscribe(params => {
        this.groupID = +params['groupID'];
        console.log('groupID: ', this.groupID);
        this.taskID = +params['taskID'];
        console.log('taskID: ', this.taskID);
      });

    this.message = new Message('success', '');

    this.groupID = this.route.snapshot.params['groupID'];

    this.sub2 = this.taskService.getTaskForGroup(this.groupID)
      .subscribe((tasks: Task[]) => {
        this.tasks = tasks;
        this.isLoaded = true;
        console.log(this.tasks);
      });
  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
    if (this.sub2) {
      this.sub2.unsubscribe();
    }
  }

  onSubmit(form: NgForm) {
    console.log(form.value['answer']);
    const answer = form.value['answer'];
    const strDate = new Date().toISOString().slice(0, -5);
    this.solutionService.sendSolution(new Solution('nope', answer, this.taskID,
        this.authService.getId(), '', strDate ))
      .then((res) => {
        this.showMessage({
          text: 'Решение успешно отправлено!',
          type: 'info'});
      })
      .catch(reason => {
        this.showMessage({
          text: 'Вы уже отправляли решение на это задание!',
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
