import { Injectable } from '@angular/core';
import { HttpService } from '../../http/http.service';

@Injectable({
    providedIn: 'root'
})
export class CompanyService {

    url: string;

    constructor(private httpService: HttpService) {
        this.url = 'company/';
    }

    getAll() {
        return this.httpService.get(this.url);
    }
}
