import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource, MatDialog } from '@angular/material';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/core/services/user/user.service';

@Component({
  selector: 'teamup-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent {

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  dataSource: MatTableDataSource<User>;
  displayedColumns: string[] = ['firstName', 'lastName', 'email'];

  constructor(private userService: UserService, private userDetails: MatDialog) {
    this.getData();
  }

  getData() {
    this.userService.getAll().subscribe((data) => {
      this.dataSource = new MatTableDataSource<User>(data as User[]);
      this.dataSource.sort = this.sort;
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
