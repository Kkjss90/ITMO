import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ElementService {

  private baseUrl = 'http://localhost:8080/server-1.0-SNAPSHOT/api/results';

  constructor(private httpClient: HttpClient) {
  }

  addElement(element: any): Observable<any> {
    const url = `${this.baseUrl}`;

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem("refreshToken")}`
    });

    return this.httpClient.post(url, element, {headers});

  }

  getAllElements(element: any): Observable<any> {
    const url = `${this.baseUrl}`;

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem("refreshToken")}`
    });

    return this.httpClient.get(url, {headers});
  }

  clearAllElements(element: any): Observable<any>{
    const url = `${this.baseUrl}`;

    // const headers = new HttpHeaders({
    //   'Authorization': `Bearer ${localStorage.getItem("refreshToken")}`
    // });
    return this.httpClient.delete(url, element);  }
}
