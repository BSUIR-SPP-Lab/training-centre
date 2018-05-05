import { Component, OnInit } from '@angular/core';
import { User } from '../../../../shared/models/user.model';
import { AuthService } from '../../../../shared/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'tc-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  date: Date = new Date();
  user: User;
  userName = 'Гость';
  isLogginIn = false;
  role: string;


  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    if (this.authService.isLoggedIn()) {
      this.user = JSON.parse(window.localStorage.getItem('user'));
      this.userName = this.user.firstName;
      this.isLogginIn = true;
    }

    this.role = this.authService.getRole();
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/system', 'start-page']);
  }


}
