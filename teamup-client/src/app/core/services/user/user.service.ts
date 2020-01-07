
...........................................................import { Injectable } from '@angular/core';
import { HttpService } from '../../http/http.service';

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
    return this.httpService.put(this.url + path, data);
  }
    getById(id: number) {
        return this.httpService.get(this.url + id);
    }

    save(user: User) {
      return this.httpService.post(this.url+'save', user);
    }
}
