import {Injectable} from '@angular/core';
import {HttpService} from '../../http/http.service';
import {User} from "../../../shared/models/user";

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

    save(user: User) {
      return this.httpService.post(this.url+'save', user);
    }
}