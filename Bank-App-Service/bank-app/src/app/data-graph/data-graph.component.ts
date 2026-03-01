import { Component } from '@angular/core';
import { User } from '../models/User';
import { SharedService } from '../shared/service/shared.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-data-graph',
  imports: [CommonModule],
  templateUrl: './data-graph.component.html',
  styleUrl: './data-graph.component.css'
})
export class DataGraphComponent {
   
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
        this.currentUser = user;
        console.log("Current user Subject:", this.currentUser);
      });
  
      this.sharedService.currentUserBehaviour.subscribe(user => {
        this.currentUser = user;
        console.log("Current user behavior:", this.currentUser);
      });
    }

    updateUser() {
      if(this.currentUser) {
        this.currentUser.email = 'kali.doe@example.com';
        this.currentUser.bio = 'Software developer';
      }

      this.sharedService.changeCurrentUser(this.currentUser);
    }
  }