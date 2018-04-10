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
    ListItemComponent
  ],
  providers: []
})

export class SystemModule {
}
