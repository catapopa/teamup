import { Injectable } from '@angular/core';
import { UserLogin } from '../../shared/models/userLogin';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url = 'http://localhost:1212';

  constructor(private http: HttpClient, private router: Router, public jwtHelper: JwtHelperService) { }

  public login(user: UserLogin) {


    this.http.post<any>(this.url + '/authenticate', user).subscribe(async (data) => {
      localStorage.setItem('token', 'Bearer ' + data.jwtToken);
      this.router.navigate(['home']);
    });
  }

  public signOut() {
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !this.jwtHelper.isTokenExpired(token);
  }
}


