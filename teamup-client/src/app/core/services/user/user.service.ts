import { Injectable } from '@angular/core';
import { HttpService } from '../../http/http.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url: string;

  constructor(private httpService: HttpService) {
    this.url = 'users/';
  }

  getAll() {
    return this.httpService.get(this.url);
  }

  getById(id: number) {
    return this.httpService.get(this.url + id);
  }

  delete(id: number) {
    return this.httpService.delete(this.url, id);
  }

  update(path: string, data: any) {
    return this.httpService.put(this.url + path, data);
  }
}
