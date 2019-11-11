import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class HttpService {

  url;

  constructor(private http: HttpClient) {
    this.url = environment.serviceHost;
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