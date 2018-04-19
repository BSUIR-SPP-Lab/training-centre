import { Component, OnInit } from '@angular/core';
import {SertificateService} from "../../../shared/services/sertificate.service";
import {AuthService} from "../../../shared/services/auth.service";
import {Sertificate} from "../../../shared/models/sertificate.model";
import {User} from "../../../shared/models/user.model";

@Component({
  selector: 'tc-personal-sertificate',
  templateUrl: './personal-sertificate.component.html',
  styleUrls: ['./personal-sertificate.component.scss']
})
export class PersonalSertificateComponent implements OnInit {

  sertificateList: Sertificate[] = [];
  user: User;

  constructor(private sertificateService: SertificateService,
              private authService: AuthService) { }

  ngOnInit() {
    //TODO Sub ant other
    this.user = JSON.parse(window.localStorage.getItem('user'));

    this.sertificateList = this.sertificateService.getSertificateForId(this.user.id);
    console.log(this.sertificateList);
  }

}
