import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SystemComponent } from './system.component';
import { StarPageComponent } from './start-page/start-page.component';
import {PersonalCourseComponent} from "./pages/personal-course/personal-course.component";
import {PersonalSertificateComponent} from "./pages/personal-sertificate/personal-sertificate.component";
import {RoleControllerComponent} from "./pages/role-controller/role-controller.component";
import {CourseDetailComponent} from "./pages/course-detail/course-detail.component";
import {TasksListComponent} from "./pages/tasks-list/tasks-list.component";
import {SolutionComponent} from "./pages/solution/solution.component";
import {SolutionListComponent} from "./pages/solution-list/solution-list.component";
import {CourseControllerComponent} from "./pages/course-controller/course-controller.component";
import {GroupControlComponent} from "./pages/group-control/group-control.component";
import {AplicationsComponent} from "./pages/aplications/aplications.component";
import {TaskControllComponent} from "./pages/task-controll/task-controll.component";
import {SolutionControllerComponent} from "./pages/solution-controller/solution-controller.component";
import {SolutionsGroupComponent} from "./components/solutions-group/solutions-group.component";
import {SolutionTeacherComponent} from "./components/solution-teacher/solution-teacher.component";

const routes: Routes = [
  {path: 'system', component: SystemComponent, children: [
    {path: 'start-page', component: StarPageComponent },
    {path: 'personal-course', component: PersonalCourseComponent},
    {path: 'personal-sertificate', component: PersonalSertificateComponent},
    {path: 'role-controller', component: RoleControllerComponent},
    {path: 'solutions', component: SolutionListComponent},
    {path: 'solutions-teacher', component: SolutionControllerComponent},
    {path: 'solutions-teacher/:groupID', component: SolutionsGroupComponent},
    {path: 'solutions-teacher/:groupID/:taskId/:userId', component: SolutionTeacherComponent},
    {path: 'courses', component: CourseControllerComponent},
    {path: 'groups', component: GroupControlComponent},
    {path: 'applications', component: AplicationsComponent},
    {path: 'tasks', component: TaskControllComponent},
    {path: 'course-detail/:id', component: CourseDetailComponent},
    {path: 'task-list/:id', component: TasksListComponent},
    {path: 'solution/:groupID/:taskID', component: SolutionComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class SystemRoutingModule {}
