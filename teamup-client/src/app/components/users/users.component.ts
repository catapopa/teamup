import { UserSkill } from './../../shared/models/userSkill';
import { Component, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource, MatDialog } from '@angular/material';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/core/services/user/user.service';
import { UserDetailsComponent } from '../user-details/user-details.component';

@Component({
    selector: 'teamup-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.scss']
})
export class UsersComponent {

    @ViewChild(MatSort, { static: true }) sort: MatSort;

    dataSource: MatTableDataSource<User>;
    displayedColumns: string[] = ['firstName', 'lastName', 'email', 'company', 'location', 'menu'];

    constructor(private userService: UserService, private userDetails: MatDialog) {
        this.getData();
    }

    getData() {
        this.userService.getAll().subscribe((data) => {
            console.log(data)

            this.dataSource = new MatTableDataSource<User>(data as User[]);
            this.dataSource.sort = this.sort;
        });
    }

    applyFilter(filterValue: string) {
        this.dataSource.filter = filterValue.trim().toLowerCase();
    }

    delete(user: User) {
        this.userService.delete(user.id).subscribe(
            () => {
                this.getData();
            },
            () => {
            }
        );
    }

    getDetails(element) {
        this.userDetails.open(UserDetailsComponent, {
            data: {
                skills: element.skills
            }
        });
    }
}
