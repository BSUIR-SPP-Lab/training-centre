import {Component, OnDestroy, OnInit} from '@angular/core';
import {SolutionService} from "../../../shared/services/solution.service";
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute} from "@angular/router";
import {SolutionInfo} from "../../../shared/models/solutionInfo.model";

@Component({
  selector: 'tc-solutions-group',
  templateUrl: './solutions-group.component.html',
  styleUrls: ['./solutions-group.component.scss']
})
export class SolutionsGroupComponent implements OnInit,OnDestroy {

  isLoaded = false;
  sub1: Subscription;
  groupId: number;
  solutions: SolutionInfo[] = [];
  constructor(
    private route: ActivatedRoute,
    private solutionService: SolutionService) { }

  ngOnInit() {
    this.groupId = this.route.snapshot.params['groupID'];
    console.log('groupID =', this.groupId);
     this.sub1 = this.solutionService.getSolutionForGroup(this.groupId)
       .subscribe((solutions: SolutionInfo[]) => {
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
