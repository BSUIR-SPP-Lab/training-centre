import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SystemComponent } from './system.component';
import { StarPageComponent } from './start-page/start-page.component';
import {PersonalCourseComponent} from "./pages/personal-course/personal-course.component";
import {PersonalSertificateComponent} from "./pages/personal-sertificate/personal-sertificate.component";
import {RoleControllerComponent} from "./pages/role-controller/role-controller.component";

const routes: Routes = [
  {path: 'system', component: SystemComponent, children: [
    {path: 'start-page', component: StarPageComponent },
    {path: 'personal-course', component: PersonalCourseComponent},
    {path: 'personal-sertificate', component: PersonalSertificateComponent},
    {path: 'role-controller', component: RoleControllerComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class SystemRoutingModule {}
