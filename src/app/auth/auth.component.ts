import {Component, HostBinding, OnDestroy, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {fadeStateTrigger} from "../shared/animations/fade.animation";

@Component ({
  selector: 'tc-auth',
  templateUrl: './auth.component.html',
  animations: [fadeStateTrigger]
})

export class AuthComponent implements OnInit, OnDestroy {


  @HostBinding('@fade') a = true;
  destroy = true;
  constructor(private router: Router) {}
  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.destroy = false;
  }

}
