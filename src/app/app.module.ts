import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import { HttpModule } from '@angular/http';

import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AuthModule} from "./auth/auth.module";
import {SystemModule} from "./system/system.module";
import {UsersService} from "./shared/services/users.service";
import {AuthService} from "./shared/services/auth.service";
import {CourseService} from "./shared/services/course.service";
import {SertificateService} from "./shared/services/sertificate.service";
import {ApplicationService} from "./shared/services/application.service";
import {GroupService} from "./shared/services/group.service";
import {TaskService} from "./shared/services/task.service";
import {SolutionService} from "./shared/services/solution.service";
import {CourseInfoService} from "./shared/services/courseInfo.service";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    HttpModule,
    SystemModule,
    BrowserAnimationsModule
  ],
  providers: [
    UsersService,
    AuthService,
    CourseService,
    SertificateService,
    ApplicationService,
    GroupService,
    TaskService,
    SolutionService,
    CourseInfoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
