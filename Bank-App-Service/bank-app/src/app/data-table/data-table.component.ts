import { Component } from '@angular/core';
import { SharedService } from '../shared/service/shared.service';
import { User } from '../models/User';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-data-table',
  imports: [CommonModule],
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent {

  currentUser: User| null = null;

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

    this.sharedService.currentUserSubjectObservable.subscribe(user => {
      let thisUser = user;
      console.log("Current user Subject:", thisUser);
    });

    this.sharedService.currentUserBehaviour.subscribe(user => {
      let thisUser = user;
      console.log("Current user behavior:", thisUser);
    });

    this.sharedService.changeCurrentUser(this.currentUser);
  }

}
