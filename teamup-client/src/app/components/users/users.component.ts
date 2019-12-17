import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource, MatDialog } from '@angular/material';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/core/services/user/user.service';
import { ConfirmPromptService } from 'src/app/shared/snack/snack.service';

@Component({
  selector: 'teamup-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent {

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  selectedRole = 'all';
  dataSource: MatTableDataSource<User>;
  displayedColumns: string[] = ['firstName'];

  constructor(private userService: UserService, private userDetails: MatDialog) {
    this.getData();
  }

  getData() {
    this.userService.getAll().subscribe((data) => {
      this.dataSource = new MatTableDataSource<User>(data as User[]);
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
