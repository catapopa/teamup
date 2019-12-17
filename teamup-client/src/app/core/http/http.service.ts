import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { AuthService } from '../authentication/auth.service';

@Injectable({
  providedIn: 'root'
})

export class HttpService {

  url: string;
  // token: string;

  constructor(private http: HttpClient, private authService: AuthService) {
    this.url = environment.serviceHost;
    // this.token = localStorage.getItem('token');
  }

  get(path: string) {
    return this.http.get(this.url + path);
  }

  post(path: string, data: any) {
    return this.http.post(this.url + path, data);
  }

  put(path: string, data: any) {
    return this.http.put(this.url + path, data);
  }

  delete(path: string, id: any) {
    return this.http.delete(this.url + path + id);
  }
}
