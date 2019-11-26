import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {TechnologyArea} from "../../models/technologyArea";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TechAreaService {
  baseUrl = environment.serviceHost;

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<TechnologyArea[]>{
    return this.httpClient.get<TechnologyArea[]>(this.baseUrl+"technology-area")
  }

  save(techArea: TechnologyArea){
    this.httpClient.post<TechnologyArea>(this.baseUrl+"technology-area/save",techArea);
  }
}
