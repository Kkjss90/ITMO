import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TableDataService {
  private clearTableSubject = new Subject<void>();

  clearTable$ = this.clearTableSubject.asObservable();

  clearTable() {
    this.clearTableSubject.next();
  }
}
