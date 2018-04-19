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
    SertificateService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
