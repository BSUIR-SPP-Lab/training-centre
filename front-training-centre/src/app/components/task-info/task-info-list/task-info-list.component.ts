import { AfterViewInit, Component, OnInit} from '@angular/core';
import { TaskTemplateService } from '../../../services/taskTemplate.service';
import {NavigationEnd, NavigationStart, Router, RouterEvent} from '@angular/router';


class TaskTemplate {
  body: string;
  name: string;
  taskInfoId: number;
}

@Component({
  selector: 'app-task-info-list',
  templateUrl: './task-info-list.component.html',
  styleUrls: ['./task-info-list.component.css']
})
export class TaskInfoListComponent implements OnInit, AfterViewInit {

  tasks: TaskTemplate[] = [];
  task: TaskTemplate;
  constructor(private taskTemplateService: TaskTemplateService, private router: Router) {
    router.events.filter(event => (event instanceof RouterEvent) ).subscribe(event => {
      if ((event['url'] === '/taskTemplate')) {
        if (event instanceof NavigationStart || event instanceof  NavigationEnd) {
          // console.log('lasId', this.taskTemplateService.lastId);
          // console.log('eventId', +event['id']);
          // this.taskTemplateService.lastId++;
          // console.log(event.toString());
          this.loadTaskTemplate();
          console.log('update url (task-template)', event instanceof RouterEvent);
        }
      }

    });

  }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.task = new TaskTemplate();
  }


  loadTaskTemplate() {
    this.taskTemplateService
      .getTaskTemplates()
      .subscribe( (tasks: TaskTemplate[]) => {
        this.tasks = tasks;
      },
        (error) => {
          alert(error);
        }
      );
  }
}
