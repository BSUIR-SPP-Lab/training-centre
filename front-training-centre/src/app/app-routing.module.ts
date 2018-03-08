import { NgModule } from '@angular/core';
import { UsersListComponent } from './components/users-list/users-list.component';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './components/user/user.component';
import {StartPageComponent} from "./components/start-page/start-page.component";



const appRoutes: Routes = [
    {path: '', component: StartPageComponent},
   {path: 'users', component: UsersListComponent, children: [
     { path: ':id', component: UserComponent }
   ] }
]

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})

export class AppRoutingModule {

}
