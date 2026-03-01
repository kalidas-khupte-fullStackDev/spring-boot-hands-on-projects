import { Injectable } from '@angular/core';
import { Subject, BehaviorSubject, distinctUntilChanged } from 'rxjs';
import { User } from '../../models/User';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

    private currentUserSubject = new Subject<User |null>();
    public currentUserSubjectObservable = this.currentUserSubject.asObservable().pipe(distinctUntilChanged());

  private currentUserBehaviourSubject = new BehaviorSubject<User | null>(null);
  public currentUserBehaviour = this.currentUserBehaviourSubject.asObservable().pipe(distinctUntilChanged());

  constructor() { }

  changeCurrentUser(user: User | null) {
    this.currentUserSubject.next(user);
    this.currentUserBehaviourSubject.next(user);
  }

}
