import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { UsersListComponent } from './components/users-list/users-list.component';
import { UsersService } from './services/users.service';
import { AppRoutingModule } from './app-routing.module';
import { UserComponent } from './components/user/user.component';
import { StartPageComponent } from './components/start-page/start-page.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { TaskInfoListComponent } from './components/task-info/task-info-list/task-info-list.component';
import { TaskTemplateService } from './services/taskTemplate.service';
import { TasktemplateComponent } from './components/task-info/tasktemplate/tasktemplate.component';
import { CourseInfoListComponent } from './components/course-info/course-info-list/course-info-list.component';
import { CourseTemplateService } from './services/courseTemplate.service';
import { CourseTemplateComponent } from './components/course-info/course-template/course-template.component';
import { TaskService } from './services/task.service';
import { TaskListComponent } from './components/task/task-list/task-list.component';
import { TaskComponent } from './components/task/task/task.component';
import { GroupService } from './services/group.service';
import { CourseListComponent } from './components/course/course-list/course-list.component';
import { CourseService } from './services/course.service';
import { CourseComponent } from './components/course/course/course.component';



@NgModule({
  declarations: [
    AppComponent,
    UsersListComponent,
    UserComponent,
    StartPageComponent,
    NotFoundComponent,
    TaskInfoListComponent,
    TasktemplateComponent,
    CourseInfoListComponent,
    CourseTemplateComponent,
    TaskListComponent,
    TaskComponent,
    CourseListComponent,
    CourseComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [
    UsersService,
    TaskTemplateService,
    CourseTemplateService,
    TaskService,
    GroupService,
    CourseService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
