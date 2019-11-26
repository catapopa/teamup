import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Company} from "../../models/company";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  baseUrl = environment.serviceHost;

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Company[]>{
    return this.httpClient.get<Company[]>(this.baseUrl+"company")
  }

  save(company: Company){
    this.httpClient.post<Company>(this.baseUrl+"company/save",company);
  }
}
