import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Location} from "../../models/location";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  baseUrl = environment.serviceHost;

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Location[]>{
    return this.httpClient.get<Location[]>(this.baseUrl+"location")
  }

  save(location: Location){
    this.httpClient.post<Location>(this.baseUrl+"location/save",location);
  }
}
