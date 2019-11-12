import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../models/user';
import {AuthService} from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'http://localhost:1212';

  constructor(private http: HttpClient, private authService: AuthService) {
  }

  public getAll(): Observable<User[]> {
      return this.http.get<User[]>(this.baseUrl + '/users');
  }
}
