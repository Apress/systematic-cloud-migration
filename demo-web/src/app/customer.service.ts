import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { Observable, throwError  } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

import { Customer } from './customer';
import { Message } from './message';

import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:8080/api/customers';

  constructor(private http: HttpClient) { }

  public buildHeaders(): HttpHeaders {

    const headers = new HttpHeaders();
    // headers.append('Content-Type',  'application/json');
    headers.append('Access-Control-Allow-Headers', 'Content-Type');

    headers.append('Access-Control-Allow-Origin', '*');

    // Website you wish to allow to connect
    // headers.append('Access-Control-Allow-Origin', 'http://localhost:8100');

    // Request methods you wish to allow
    headers.append('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    headers.append('Access-Control-Allow-Credentials', 'true');

  

    // Request headers you wish to allow
    // tslint:disable-next-line: max-line-length
    headers.append('Access-Control-Allow-Headers', 'Access-Control-Allow-Origin, Access-Control-Allow-Headers, Access-Control-Allow-Origin, Content-Type,  Access-Control-Allow-Credentials, Access-Control-Allow-Methods, Authorization, X-Requested-With');

    return headers;
}

  /**
   * Do a posting Customer
   * @param customer 
   */
  createCustomer(customer: Customer): Observable<Message> {
    const options = { headers: this.buildHeaders()};
      return this.http.post<Message>(`${this.baseUrl}` + `/create`, customer, options)
                  .pipe(
                    retry(3),
                    catchError(this.handleError)
                  );
  }

  updateCustomer(customer: Customer): Observable<Message> {
    const options = { headers: this.buildHeaders()};
      return this.http.put<Message> (`${this.baseUrl}` + `/updatebyid/` + customer.id, customer, options)
        .pipe(
            retry(3),
            catchError(this.handleError)
          );
  }

  deleteCustomer(id: number): Observable<Message> {
    const options = { headers: this.buildHeaders()};
      return this.http.delete<Message>(`${this.baseUrl}` + `/deletebyid/` + id, options)
            .pipe(
              retry(3),
              catchError(this.handleError)  
            );
  }

  /**
   * Retrieve all customer from Backend
   */
  retrieveAllCustomers(): Observable<Message> {
    const options = { headers: this.buildHeaders()};
      return this.http.get<Message>(`${this.baseUrl}` + `/retrieveinfos`, options)
                    .pipe(
                      retry(3),
                      catchError(this.handleError)
                    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
}