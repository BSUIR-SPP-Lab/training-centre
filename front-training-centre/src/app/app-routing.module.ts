import { NgModule } from "@angular/core";
import { UsersListComponent } from "./components/users-list/users-list.component";
import { Routes, RouterModule } from '@angular/router';


const appRoutes: Routes = [
    {path: '', component: UsersListComponent}
]

@NgModule({
    imports:[RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})

export class AppRoutingModule {

}