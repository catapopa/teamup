import { Injectable } from '@angular/core';
import { HttpService } from '../../http/http.service';

@Injectable({
  providedIn: 'root'
})
export class IndustryService {
  url: string;

    constructor(private httpService: HttpService) {
        this.url = 'industry/';
    }

    getAll() {
        return this.httpService.get(this.url);
    }
}
