import { Injectable } from '@angular/core';
import { UserLogin } from '../../shared/models/userLogin';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
// Ignore cause:
// This module is declared with using 'export =', and can only be used with a default
// import when using the 'allowSyntheticDefaultImports' flag.
// @ts-ignore
import decode from 'jwt-decode';

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

  public getRole() {
    const token = localStorage.getItem('token');
    const tokenPayload = decode(token);
    return tokenPayload.role;
  }

  public getUsername() {
    const token = localStorage.getItem('token');
    const tokenPayload = decode(token);
    return tokenPayload.username;
  }
}
