import { Injectable } from '@angular/core';
import { HttpService } from '../../http/http.service';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  url: string;

    constructor(private httpService: HttpService) {
        this.url = 'location/';
    }

    getAll() {
        return this.httpService.get(this.url);
    }

}
