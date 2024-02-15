import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClearCanvasService {
  private clearCanvasSubject = new Subject<void>();

  clearCanvas$ = this.clearCanvasSubject.asObservable();

  clearCanvas() {
    this.clearCanvasSubject.next();
  }
}
