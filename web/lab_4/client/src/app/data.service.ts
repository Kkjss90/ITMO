import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private baseUrl = 'http://localhost:8080/api/auth';

  constructor(private httpClient: HttpClient) {}
// Метод для отправки предварительного запроса OPTIONS
sendOptionsRequest() {
  // Установите необходимые заголовки для предварительного запроса
  const headers = new HttpHeaders()
    .set('Content-Type', 'application/json')
    .set('Access-Control-Request-Method', 'POST') // Укажите метод, который вы планируете использовать
    .set('Access-Control-Request-Headers', 'authorization, content-type'); // Укажите заголовки, которые вы планируете отправить

  // Отправьте предварительный запрос OPTIONS
  this.httpClient.options(this.baseUrl, { headers }).subscribe(
    () => {
      console.log('Предварительный запрос OPTIONS успешно отправлен');
      // Обработайте успешный ответ
    },
    error => {
      console.error('Ошибка при отправке предварительного запроса OPTIONS:', error);
      // Обработайте ошибку
    }
  );
}

  registerUser(data: any): Observable<any> {
    const url = `${this.baseUrl}/reg`;

    return this.httpClient.post(url, data);
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
