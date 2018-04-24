import {NgModule} from '@angular/core';
import {HeaderComponent} from './shared/components/header/header.component';
import {SidebarComponent} from './shared/components/sidebar/sidebar.component';
import {StarPageComponent} from './start-page/start-page.component';
import {SystemComponent} from "./system.component";
import {SharedModule} from "../shared/shared.module";
import {SystemRoutingModule} from "./system.routing.module";
import {DropdownDirective} from "./shared/directives/dropdown.directive";
import {CommonModule} from "@angular/common";
import { CourseListComponent } from './start-page/course-list/course-list.component';
import { ListItemComponent } from './start-page/course-list/list-item/list-item.component';
import { PersonalCourseComponent } from './pages/personal-course/personal-course.component';
import {MomentPipe} from "./shared/pipes/moment.pipe";
import { PersonalSertificateComponent } from './pages/personal-sertificate/personal-sertificate.component';
import { RoleControllerComponent } from './pages/role-controller/role-controller.component';
import { CourseDetailComponent } from './pages/course-detail/course-detail.component';
import { TasksListComponent } from './pages/tasks-list/tasks-list.component';


@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    SystemRoutingModule
  ],
  declarations: [
    SystemComponent,
    HeaderComponent,
    SidebarComponent,
    StarPageComponent,
    DropdownDirective,
    CourseListComponent,
    ListItemComponent,
    MomentPipe,
    PersonalCourseComponent,
    PersonalSertificateComponent,
    RoleControllerComponent,
    CourseDetailComponent,
    TasksListComponent
  ],
  providers: []
})

export class SystemModule {
}
