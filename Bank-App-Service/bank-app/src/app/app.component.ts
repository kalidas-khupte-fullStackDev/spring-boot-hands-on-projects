import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { filter, map } from 'rxjs';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'bank-app';
  viewCustomerViaId$: any;
  allCustomers$: any;

  

  constructor(private http: HttpClient) {
    // this.viewCustomerViaId$ = this.http.get('http://localhost:8080/api/customers/view/1');
    // this.allCustomers$ = this.http.get('http://localhost:8080/api/customers/view/all');
  }

  ngOnInit() {
    // this.allCustomers$.pipe(map((customers: any[]) => customers.filter(customer => customer.id == '1')) ).subscribe((filteredCustomers: any[]) => {
    //   console.log("With pipe operator:", filteredCustomers);
    // });

    // this.allCustomers$.subscribe((response: any) => {
    //   console.log("Without pipe operator:", response);
    // });

    // // this.getCustomers$.unsubscribe();

    // this.viewCustomerViaId$.subscribe((response: any) => {
    //   console.log("With id:", response);
    // });

  }
}
