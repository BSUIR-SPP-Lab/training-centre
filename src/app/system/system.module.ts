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
import { SolutionComponent } from './pages/solution/solution.component';
import { SolutionListComponent } from './pages/solution-list/solution-list.component';
import { CourseInfoComponent } from './components/course-info/course-info.component';
import { CourseControllerComponent } from './pages/course-controller/course-controller.component';
import { CourseComponent } from './components/course/course.component';
import { GroupControlComponent } from './pages/group-control/group-control.component';
import { AddGroupComponent } from './components/add-group/add-group.component';
import { AddTeacherToGroupComponent } from './components/add-teacher-to-group/add-teacher-to-group.component';
import { AplicationsComponent } from './pages/aplications/aplications.component';
import { AplicationApproveComponent } from './components/aplication-approve/aplication-approve.component';
import { TaskControllComponent } from './pages/task-controll/task-controll.component';
import { TasksInfoComponent } from './components/tasks-info/tasks-info.component';
import { TasksAddComponent } from './components/tasks-add/tasks-add.component';


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
    TasksListComponent,
    SolutionComponent,
    SolutionListComponent,
    CourseInfoComponent,
    CourseControllerComponent,
    CourseComponent,
    GroupControlComponent,
    AddGroupComponent,
    AddTeacherToGroupComponent,
    AplicationsComponent,
    AplicationApproveComponent,
    TaskControllComponent,
    TasksInfoComponent,
    TasksAddComponent
  ],
  providers: []
})

export class SystemModule {
}
