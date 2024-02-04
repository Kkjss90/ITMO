import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SharedDataService {
  private rValueSubject = new BehaviorSubject<number>(0);
  R$: Observable<number> = this.rValueSubject.asObservable();

  setRValue(r: number): void {
    this.rValueSubject.next(r);
  }
}
