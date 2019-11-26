import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Industry} from "../../models/industry";
import {Observable} from "rxjs";
import {Company} from "../../models/company";

@Injectable({
  providedIn: 'root'
})
export class IndustryService {
  baseUrl = environment.serviceHost;

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Industry[]>{
    return this.httpClient.get<Industry[]>(this.baseUrl+"industry")
  }

  save(industry: Industry){
    this.httpClient.post<Industry>(this.baseUrl+"industry/save",industry);
  }
}
