import {Component, OnDestroy, OnInit} from '@angular/core';
import {SertificateService} from "../../../shared/services/sertificate.service";
import {AuthService} from "../../../shared/services/auth.service";
import {Sertificate} from "../../../shared/models/sertificate.model";
import {User} from "../../../shared/models/user.model";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'tc-personal-sertificate',
  templateUrl: './personal-sertificate.component.html',
  styleUrls: ['./personal-sertificate.component.scss']
})
export class PersonalSertificateComponent implements OnInit, OnDestroy {

  routeSertificatePDF = 'pdfCertificate/';
  routeSertificateCVS = 'cvsCertificate/';
  routeSertificateXLS = 'pdfCertificate/';

  documentServerRoute: string;
  sertificateList: Sertificate[] = [];
  isLoaded = false;
  user: User;
  sub1: Subscription;

  constructor(private sertificateService: SertificateService,
              private authService: AuthService) { }

  ngOnInit() {
    this.sub1 = this.sertificateService.getSertificateForUserId(this.authService.getId())
      .subscribe((sertificats: Sertificate[]) => {
        this.sertificateList = sertificats;
        console.log(this.sertificateList);
        this.documentServerRoute = this.sertificateService.getDocumentServerRoute();
        this.isLoaded = true;
      });
  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

}
