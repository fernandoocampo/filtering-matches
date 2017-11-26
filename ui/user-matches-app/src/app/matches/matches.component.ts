import { Component, OnInit } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { of } from 'rxjs/observable/of';

import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';

import { User } from '../user';
import { Result } from '../result';
import { UserFilter } from '../user-filter';
import { UserFinderService } from '../user-finder.service';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css']
})
export class MatchesComponent implements OnInit {

  result: Result;
  scores: number[];
  ages: number[];
  heights: number[];
  users: User[];

  model = new UserFilter();

  constructor(private userFinderService: UserFinderService) { }

  ngOnInit() {
    this.scores = Array(99).fill(99,0,99).map((x,i)=>i+1);
    this.ages = Array(79).fill(79,1,79).map((x,i)=>i+17);
    this.heights = Array(76).fill(76,1,76).map((x,i)=>i+134);
  }

  /**
   * it is in charge to convert the service result in a array.
   * @param val 
   */
  hack(val) {
    if (val != null) {
      return Array.from(val);
    }
  }

  /**
   * invokes the service to search users that match the filters.
   */
  getUsers(): any {
    this.userFinderService.getUsers(this.model)
    .subscribe((result: Result) => { 
      this.result = <Result>result;
      this.users = this.result.userdata;      
    });
    return "";
  }

}
