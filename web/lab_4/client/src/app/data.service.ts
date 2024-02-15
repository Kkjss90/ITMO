import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private baseUrl = 'http://localhost:8080/server-1.0-SNAPSHOT/api/auth';

  constructor(private httpClient: HttpClient) {}

  registerUser(data: any): Observable<any> {
    const url = `${this.baseUrl}/reg`;
    return this.httpClient.post(url, data);
  }

  loginUser(data: any): Observable<any> {
    const url = `${this.baseUrl}/log`;

    return this.httpClient.post(url, data);
  }

  logoutUser(): Observable<any> {
    const url = `${this.baseUrl}/logout`;

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem("refreshToken")}`
    });

    return this.httpClient.delete(url, { headers });
  }
  refresh(data: any): Observable<any>{
    const url = `${this.baseUrl}/refresh`;

    return this.httpClient.post(url, data);
  }
}
