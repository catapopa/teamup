import { Component, OnInit } from '@angular/core';
import {UserService} from '../../core/services/user/user.service';
import {User} from '../../core/models/user';

@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
  }
  getAll(){
    this.userService.getAll().subscribe((data) => console.log(data), (error1 => console.log(error1)) );
  }

}
