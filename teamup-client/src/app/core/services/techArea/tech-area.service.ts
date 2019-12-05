import { Injectable } from '@angular/core';
import { HttpService } from '../../http/http.service';

@Injectable({
    providedIn: 'root'
})
export class TechAreaService {
    url: string;

    constructor(private httpService: HttpService) {
        this.url = 'technology-area/';
    }

    getAll() {
        return this.httpService.get(this.url);
    }

}
