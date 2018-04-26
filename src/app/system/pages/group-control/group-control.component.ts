import {Component, OnDestroy, OnInit} from '@angular/core';
import {Group} from "../../../shared/models/group.model";
import {GroupService} from "../../../shared/services/group.service";
import {Subscription} from "rxjs/Subscription";
import {Course} from "../../../shared/models/course.model";

@Component({
  selector: 'tc-group-control',
  templateUrl: './group-control.component.html',
  styleUrls: ['./group-control.component.scss']
})
export class GroupControlComponent implements OnInit, OnDestroy {

  groups: Group[] = [];
  courses: Course[] = [];
  isLoaded = false;
  sub1: Subscription;
  constructor(private groupService: GroupService) { }

  ngOnInit() {
    this.sub1 = this.groupService.getGroups()
      .subscribe((groups: Group[]) => {
        this.groups = groups;
        this.isLoaded = true;
      });
  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

  onGroupAdded(group: Group) {
    this.sub1 = this.groupService.getGroups()
      .subscribe((groups: Group[]) => {
        this.groups = groups;
      });
  }
}
