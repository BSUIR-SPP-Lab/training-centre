import { Component, OnInit, AfterViewInit } from '@angular/core';

import { Router, RouterEvent, Data } from '@angular/router';
import {TaskService} from '../../../services/task.service';


class Task {
  groupId: number;
  taskId: number;
  taskInfoId: number;
  teacherId: number;
  uploadTime: Data;
}



@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})


export class TaskListComponent implements OnInit, AfterViewInit {

  tasks: Task[] = [];
  task: Task;


  constructor(private taskTemplateService: TaskService, private router: Router) {
    router.events.filter(event => event instanceof RouterEvent).subscribe(event => {
      if (event['url'] === '/task' ) {
        this.loadTask();
        console.log(event['url'], 'update url (task)');
      }
    });

  }

  ngOnInit() {
  }


  ngAfterViewInit() {
    this.task = new Task();
    // this.loadTaskTemplate();
  }

  loadTask() {
    this.taskTemplateService
      .getTasks()
      .subscribe( (tasks: Task[]) => {
          this.tasks = tasks;
        },
        (error) => {
          alert(error);
        }
      );
  }


}
