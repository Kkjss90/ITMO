import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private baseUrl = 'http://localhost:8080/api/auth';

  constructor(private httpClient: HttpClient) {}

  registerUser(data: any): Observable<any> {
    const url = `${this.baseUrl}/reg`;
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Access-Control-Request-Method', 'POST')
      .set('Access-Control-Request-Headers', 'authorization, content-type');


    return this.httpClient.post(url, data, {headers});
  }

  loginUser(data: any): Observable<any> {
    const url = `${this.baseUrl}/log`;

    return this.httpClient.post(url, data);
  }

  logoutUser(data:any): Observable<any> {
    const url = `${this.baseUrl}/logout`;

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem("sessionId")}`
    });

    return this.httpClient.delete(url, { headers });
  }
}
