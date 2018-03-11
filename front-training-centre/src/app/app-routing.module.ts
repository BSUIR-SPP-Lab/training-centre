import { NgModule } from '@angular/core';
import { UsersListComponent } from './components/users-list/users-list.component';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './components/user/user.component';
import { StartPageComponent } from './components/start-page/start-page.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { TaskInfoListComponent } from './components/task-info/task-info-list/task-info-list.component';
import { TasktemplateComponent } from './components/task-info/tasktemplate/tasktemplate.component';
import { CourseInfoListComponent } from './components/course-info/course-info-list/course-info-list.component';
import { CourseTemplateComponent } from './components/course-info/course-template/course-template.component';
import { TaskListComponent } from './components/task/task-list/task-list.component';
import { TaskComponent } from './components/task/task/task.component';
import { CourseListComponent } from './components/course/course-list/course-list.component';
import { CourseComponent } from './components/course/course/course.component';



const appRoutes: Routes = [
    { path: '', component: StartPageComponent},
    { path: 'users', component: UsersListComponent, children: [
      { path: ':id', component: UserComponent },
      { path: 'add', component: UserComponent}
      ] },
    { path: 'taskTemplate', component: TaskInfoListComponent, children: [
      { path: ':id', component: TasktemplateComponent  },
      { path: 'add', component: TasktemplateComponent  }
    ] },
  { path: 'courseTemplate', component: CourseInfoListComponent, children: [
    { path: ':id', component: CourseTemplateComponent  },
    { path: 'add', component: CourseTemplateComponent  }
  ] },
  { path: 'task', component: TaskListComponent, children: [
    { path: ':id', component: TaskComponent  },
    { path: 'add', component: TaskComponent  }
  ] },
  { path: 'course', component: CourseListComponent, children: [
    { path: ':id', component: CourseComponent  },
    { path: 'add', component: CourseComponent  }
  ] },
    { path: '**', component: NotFoundComponent}
    ]

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})

export class AppRoutingModule {

}
