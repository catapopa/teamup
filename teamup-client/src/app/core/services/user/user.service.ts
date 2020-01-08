import { Injectable } from '@angular/core';
import { HttpService } from '../../http/http.service';
import {User} from "../../../shared/models/user";
import {Observable} from "rxjs";

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
    console.log(this.url + path);
    console.log(data);
    return this.httpService.post(this.url + path, data);
  }

  getUserByUsername(path: string, username: string) {
    return this.httpService.get(this.url + path + username);
  }

}
