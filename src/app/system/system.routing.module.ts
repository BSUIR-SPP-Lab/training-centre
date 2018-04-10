import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SystemComponent } from './system.component';
import { StarPageComponent } from './start-page/start-page.component';

const routes: Routes = [
  {path: 'system', component: SystemComponent, children: [
    {path: 'start-page', component: StarPageComponent }
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class SystemRoutingModule {}
