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


@NgModule({
  declarations: [
    AppComponent,
    UsersListComponent,
    UserComponent,
    StartPageComponent,
    NotFoundComponent,
    TaskInfoListComponent,
    TasktemplateComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [UsersService, TaskTemplateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
