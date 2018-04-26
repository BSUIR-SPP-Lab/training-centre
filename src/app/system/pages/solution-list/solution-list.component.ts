import {Component, OnDestroy, OnInit} from '@angular/core';
import {SolutionService} from "../../../shared/services/solution.service";
import {Subscription} from "rxjs/Subscription";
import {SolutionInfo} from "../../../shared/models/solutionInfo.model";
import {AuthService} from "../../../shared/services/auth.service";

@Component({
  selector: 'tc-solution-list',
  templateUrl: './solution-list.component.html',
  styleUrls: ['./solution-list.component.scss']
})
export class SolutionListComponent implements OnInit, OnDestroy {

  sub1: Subscription;
  solutions: SolutionInfo[] = [];
  isLoaded = false;

  constructor(private solutionService: SolutionService,
              private authService: AuthService) { }

  ngOnInit() {
    this.sub1 = this.solutionService.getSolutionForUser(this.authService.getId())
      .subscribe( (solutions: SolutionInfo[]) => {
        this.solutions = solutions;
        this.isLoaded = true;
      });
  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

}
