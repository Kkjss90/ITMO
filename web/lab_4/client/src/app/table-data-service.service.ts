import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TableDataService {
  private clearTableSubject = new Subject<void>();
  private updateTableSubject = new Subject<void>();

  clearTable$ = this.clearTableSubject.asObservable();

  updateTable$ = this.updateTableSubject.asObservable();

  updateTable() {
    this.updateTableSubject.next();
  }
  clearTable() {
    this.clearTableSubject.next();
  }
}
