import { NgModule } from '@angular/core';
import { UsersListComponent } from './components/users-list/users-list.component';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './components/user/user.component';
import { StartPageComponent } from './components/start-page/start-page.component';
import { NotFoundComponent } from './not-found/not-found.component';
import {TaskInfoListComponent} from './components/task-info/task-info-list/task-info-list.component';
import {TasktemplateComponent} from './components/task-info/tasktemplate/tasktemplate.component';



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
    { path: '**', component: NotFoundComponent}
    ]

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})

export class AppRoutingModule {

}
