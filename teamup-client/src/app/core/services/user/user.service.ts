import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../models/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'http://localhost:1212';

  constructor(private http: HttpClient) {
  }

  public getAll(): Observable<User[]> {
      return this.http.get<User[]>(this.baseUrl + '/users');
  }
}
