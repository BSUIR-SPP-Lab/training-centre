import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../../shared/services/auth.service';

@Component({
  selector: 'tc-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  role = '';

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.role = this.authService.getRole();
  }

}
