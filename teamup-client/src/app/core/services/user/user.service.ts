import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from "../../../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = environment.serviceHost;

  constructor(private http: HttpClient) {
  }

}
