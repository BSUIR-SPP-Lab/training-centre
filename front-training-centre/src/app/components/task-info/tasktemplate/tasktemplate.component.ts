import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import { TaskTemplateService } from '../../../services/taskTemplate.service';
import { NgForm } from '@angular/forms';

class TaskTemplate {
  body: string;
  name: string;
  taskInfoId: number;
}


@Component({
  selector: 'app-tasktemplate',
  templateUrl: './tasktemplate.component.html',
  styleUrls: ['./tasktemplate.component.css']
})
export class TasktemplateComponent implements OnInit {

  constructor(private route: ActivatedRoute, private taskTemplateService: TaskTemplateService, private router: Router) { }

  task: TaskTemplate;

  isChanged: boolean;
  isCreated: boolean;

  ngOnInit() {
    this.task = new TaskTemplate();
    this.task.taskInfoId = this.route.snapshot.params['id'];
    if ((this.route.snapshot.params['id'] !== undefined) && (this.route.snapshot.params['id'] !== 'add')) {
      this.isChanged = false;
      this.isCreated = true;
      console.log(this.route.snapshot.params['id']);
      this.task.taskInfoId = +this.route.snapshot.params['id'];
      this.loadTaskTemplates(this.task.taskInfoId);
    } else {
      this.isChanged = true;
      this.isCreated = false;
      this.task = new TaskTemplate();
    }
    this.route.params.subscribe((params: Params ) => {
      if ((this.route.snapshot.params['id'] !== undefined) && (this.route.snapshot.params['id'] !== 'add')) {
        console.log(this.route.snapshot.params['id']);
        this.isChanged = true;
        this.isCreated = false;
        this.task.taskInfoId = +this.route.snapshot.params['id'];
        this.loadTaskTemplates(this.task.taskInfoId);
      } else {
        this.isChanged = false;
        this.isCreated = true;
        this.task = new TaskTemplate();
      }
    });
  }

  loadTaskTemplates(id: number) {
    this.taskTemplateService
      .getTaskTemplate(id)
      .subscribe( (task: TaskTemplate) => {
        this.task = task;
      },
        (error) => {
        alert('Error loading template');
        });
  }

  deleteTaskTemplate() {
    this.taskTemplateService
      .deleteTaskTemplate(this.task.taskInfoId)
      .then(() => {
          console.log('Template was deleted');
          this.router.navigate(['/taskTemplate']);
        },
        (error) => {
          alert('Error while deleting template');
        });

  }

  submitForm(form: NgForm) {
    console.log(form);
    if (this.isCreated) {
      console.log('isCreated');
      // console.log(' sub ', this.user );
      this.taskTemplateService.addTaskTemplate(this.task)
        .then(_ => {
          console.log('User was added');
          this.task = new TaskTemplate();
          this.router.navigate(['/taskTemplate']);

        });

    } else {
      if (this.isChanged) {
        console.log('isChanged');
        this.taskTemplateService.updateTaskTemplate(this.task)
          .then(_ => {
            console.log('Task Template was changed');
            this.router.navigate(['/taskTemplate']);
          });
      }
    }

  }

}
