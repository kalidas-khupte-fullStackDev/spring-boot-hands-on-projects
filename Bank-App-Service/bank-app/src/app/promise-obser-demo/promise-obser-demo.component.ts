import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { AsyncSubject, BehaviorSubject, Observable, ReplaySubject, Subject } from 'rxjs';

@Component({
  selector: 'app-promise-obser-demo',
  imports: [CommonModule],
  templateUrl: './promise-obser-demo.component.html',
  styleUrl: './promise-obser-demo.component.css'
})
export class PromiseObserDemoComponent {

  myPromise: any;
  counter: number = 0;

  myObservable: any;
  myObservableSub: any;

  subject = new AsyncSubject<number>();
  myData: number[] = [];

  create() {
    this.myPromise = new Promise((resolve, reject) => {
      console.log('Promise created successfully!');
      setTimeout(() => {
        resolve('Promise resolved successfully1!');
      }, 5000);
    });

    this.myObservable = new Observable((observer) => {
      observer.next(`Observable emitted value ${++this.counter}`);
    });
  }

  execute() {
    if (this.myPromise) {
      this.myPromise.then((result: any) => {
        console.log(result);
      }).catch((error: any) => {
        console.error('Promise rejected:', error);
      });
    }
    if (this.myObservable) {
      this.myObservableSub = this.myObservable.subscribe({
        next: (value: any) => {
          setInterval(() => {
            console.log(value);
          }, 1000);
        },
        error: (err: any) => console.error('Observable error:', err),
        complete: () => console.log('Observable completed')
      });
    }
  }

  cancel() {
    this.myObservableSub.unsubscribe();
  }

  emitData() {
    this.subject.next(++this.counter);
    console.log('Emitted value:', this.counter);
    setTimeout(() => {
      this.subject.next(++this.counter);
      console.log('Emitted value:', this.counter);
    }, 3000);
    setTimeout(() => {
      this.subject.next(++this.counter);
      console.log('Emitted value:', this.counter);
    }, 6000);
    setTimeout(() => {
      this.subject.next(++this.counter);
      console.log('Emitted value:', this.counter);
    }, 9000);
    setTimeout(() => {
       this.subject.next(++this.counter);
      console.log('Emitted value:', this.counter);
    }, 12000);
  }

  subscribeData() {
    this.subject.subscribe({
      next: (value) =>{
        this.myData.push(value);
        console.log('Subject emitted value:', this.myData);
      },
      error: (err) => console.error('Subject error:', err),
      complete: () => console.log('Subject completed')
    });
  }
}