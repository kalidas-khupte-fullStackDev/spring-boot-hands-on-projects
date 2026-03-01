import { Component } from '@angular/core';
import { SharedService } from '../shared/service/shared.service';
import { User } from '../models/User';
import { RouterModule } from '@angular/router';
import { DataTableComponent } from "../data-table/data-table.component";
import { DataGraphComponent } from '../data-graph/data-graph.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [RouterModule, CommonModule, DataTableComponent, DataGraphComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  currentUser: User | null = null;

  constructor(private sharedService: SharedService) { }

  ngOnInit() {
    // Initialization logic here
    this.currentUser = {
      username: 'JohnDoe',
      email: 'john.doe@example.com',
      token: 'some-token',
      bio: 'Software developer',
      image: null
    };

    this.sharedService.changeCurrentUser(this.currentUser);

    this.sharedService.currentUserSubjectObservable.subscribe(user => {
      this.currentUser = user;
      console.log("Current user Subject:", this.currentUser);
    });

    this.sharedService.currentUserBehaviour.subscribe(user => {
      this.currentUser = user;
      console.log("Current user behavior:", this.currentUser);
    });
  }

}
