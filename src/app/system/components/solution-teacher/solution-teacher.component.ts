import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {SolutionService} from "../../../shared/services/solution.service";
import {Subscription} from "rxjs/Subscription";
import {SolutionInfo} from "../../../shared/models/solutionInfo.model";
import {Message} from "../../../shared/models/message.model";
import {NgForm} from "@angular/forms";
import {Solution} from "../../../shared/models/solution.model";

@Component({
  selector: 'tc-solution-teacher',
  templateUrl: './solution-teacher.component.html',
  styleUrls: ['./solution-teacher.component.scss']
})
export class SolutionTeacherComponent implements OnInit, OnDestroy {

  isLoaded = false;
  sub1: Subscription;
  groupId: number;
  taskId: number;
  userId: number;
  solutions: SolutionInfo[] = [];
  solution: SolutionInfo;
  mark: number;
  message: Message;

  constructor(
    private route: ActivatedRoute,
    private solutionService: SolutionService) { }

  ngOnInit() {
    this.message = new Message('success', '');
    this.groupId = +this.route.snapshot.params['groupID'];
    this.taskId = +this.route.snapshot.params['taskId'];
    this.userId = +this.route.snapshot.params['userId'];
    console.log('groupID =', this.groupId);
    this.sub1 = this.solutionService.getSolutionForGroup(this.groupId)
      .subscribe((solutions: SolutionInfo[]) => {
        this.solutions = solutions;
        console.log(this.solutions);
        console.log('taskId =', this.taskId);
        console.log('userId =', this.userId);
        const tSolution = this.solutions.filter( s => s.userId === this.userId)
          .filter(s => s.taskId === this.taskId);
        console.log(tSolution);
        this.solution = tSolution[0];
        console.log(this.solution);
        this.isLoaded = true;
      });
  }

  onSubmit(form: NgForm) {
    console.log(form.value);

    const {body, mark} = form.value;

    const solution = new Solution(this.solution.filepath, this.solution.notes,
      this.solution.taskId, this.solution.userId, body, this.solution.uploadTime, mark);


    this.solutionService.updateSolution(solution)
      .then((res) => {
        this.showMessage({
          text: 'Оценка отправлена',
          type: 'info'});
      })
      .catch(reason => {
        this.showMessage({
          text: 'Не удалось оценить',
          type: 'warning'});
      });

  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

  private showMessage(message: Message) {
    this.message = message;
    window.setTimeout( () => {
      this.message.text = '';
    }, 5000);
  }

}
