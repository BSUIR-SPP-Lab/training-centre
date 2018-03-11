import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Data, Params, Router} from '@angular/router';
import { NgForm } from '@angular/forms';
import {TaskService} from '../../../services/task.service';
import {TaskTemplateService} from '../../../services/taskTemplate.service';
import { UsersService } from '../../../services/users.service';
import { GroupService } from '../../../services/group.service';


class Task {
  groupId: number;
  taskId: number;
  taskInfoId: number;
  teacherId: number;
  uploadTime: Data;
}

class TaskTemplate {
  body: string;
  name: string;
  taskInfoId: number;
}

class User {
  email: string;
  firstName: string;
  id: number;
  lastName: string;
  login: string;
  password: string;
  phone: string;
  role: string;
}

interface Group {
  coordinatorId: number;
  courseId: number;
  groupId: number;
}

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private taskService: TaskService,
              private router: Router,
              private taskTemplateService: TaskTemplateService,
              private usersService: UsersService,
              private groupService: GroupService) { }


  taskTemplates: TaskTemplate[] = [];
  users: User[] = [];
  groups: Group[] = [];
  task: Task;

  isChanged: boolean;
  isCreated: boolean;

  ngOnInit() {
    this.loadTaskTemplate();
    this.loadUsers(); //TODO api teacher
    this.loadGroups();
    this.task = new Task();

    this.task.taskId = this.route.snapshot.params['id'];
    if ((this.route.snapshot.params['id'] !== undefined) && (this.route.snapshot.params['id'] !== 'add')) {
      this.isChanged = false;
      this.isCreated = true;
      console.log(this.route.snapshot.params['id']);
      this.task.taskId = +this.route.snapshot.params['id'];
      this.loadTask(this.task.taskId);
    } else {
      this.isChanged = true;
      this.isCreated = false;
      this.task = new Task();
    }
    this.route.params.subscribe((params: Params ) => {
      if ((this.route.snapshot.params['id'] !== undefined) && (this.route.snapshot.params['id'] !== 'add')) {
        console.log(this.route.snapshot.params['id']);
        this.isChanged = true;
        this.isCreated = false;
        this.task.taskId = +this.route.snapshot.params['id'];
        this.loadTask(this.task.taskId);
      } else {
        this.isChanged = false;
        this.isCreated = true;
        this.task = new Task();
      }
    });
  }

  loadTask(id: number) {
    this.taskService
      .getTask(id)
      .subscribe( (task: Task) => {
          this.task = task;
        },
        (error) => {
          alert('Error loading task');
        });
  }

  deleteTask() {
    this.taskService
      .deleteTask(this.task.taskId)
      .then(() => {
          console.log('Task was deleted');
          this.router.navigate(['/task']);
        },
        (error) => {
          alert('Error while deleting task');
        });

  }


  submitForm(form: NgForm) {

    console.log(this.task);

    if (this.isCreated) {
      console.log('isCreated');
       console.log('obj', this.task);
      this.taskService.addTask(this.task)
        .then(_ => {
          console.log('Task was added');
          this.task = new Task();
          this.router.navigate(['/task']);

        });

    } else {
      if (this.isChanged) {
        console.log('isChanged');
        this.taskService.updateTask(this.task)
          .then(_ => {
            console.log('Task Template was changed');
            this.router.navigate(['/task']);
          });
      }
    }



  }

  loadTaskTemplate() {
    this.taskTemplateService
      .getTaskTemplates()
      .subscribe( (tasks: TaskTemplate[]) => {
          this.taskTemplates = tasks;
        },
        (error) => {
          alert(error);
        }
      );
  }

  loadUsers() {
    this.usersService
      .getUsers().subscribe((users: User[]) => {
        users.forEach( (user: User) => {

          if (user.role === 'TEACHER') {
            this.users.push(user);
          }
        });
      },
      (error) => {
        alert(error);
      }
    );
  }

  loadGroups() {
    this.groupService
      .getGroups()
      .subscribe( (groups: Group[]) => {
          this.groups = groups;
        },
        (error) => {
          alert(error);
        }
      );
  }

}
