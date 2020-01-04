import { UserSkill } from './../../shared/models/userSkill';
import { Component, ViewChild, Inject } from '@angular/core';
import { MatSort, MatTableDataSource, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'teamup-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent {

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  dataSource: MatTableDataSource<UserSkill>;
  displayedColumns: string[] = ['name', 'area', 'level'];

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.dataSource = new MatTableDataSource<UserSkill>(this.data.skills as UserSkill[]);
    const skills = this.data.skills;
  }
}
