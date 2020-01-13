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

    getAllBySupervisor(id: number) {
        return this.httpService.get(this.url + id + '/assignedEmployees');
    }

    getById(id: number) {
        return this.httpService.get(this.url + id);
    }

    getByUsername(username: string) {
        return this.httpService.get(this.url + username);
    }

    delete(id: number) {
        return this.httpService.delete(this.url, id);
    }

    update(path: string, data: any) {
        return this.httpService.post(this.url + path, data);
    }

    getUserWithPicture(path: string, username: string) {
        return this.httpService.get(this.url + path + username);
    }

    invite(user: User) {
        return this.httpService.post(this.url + 'createAccount/', user);
    }
}
