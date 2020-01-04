import { Component, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource, MatDialog } from '@angular/material';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/core/services/user/user.service';
import { UserDetailsComponent } from '../user-details/user-details.component';
import { AuthService } from 'src/app/core/authentication/auth.service';
import { identifierModuleUrl } from '@angular/compiler';

@Component({
    selector: 'teamup-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.scss']
})
export class UsersComponent {

    @ViewChild(MatSort, { static: true }) sort: MatSort;

    dataSource: MatTableDataSource<User>;
    displayedColumns: string[] = ['firstName', 'lastName', 'email', 'company', 'location', 'menu'];
    id: number;

    constructor(private userService: UserService, private userDetails: MatDialog, private authService: AuthService) {
        this.getData();
    }

    getUserData() {
        const username = this.authService.getUsername();

        this.userService.getByUsername(username).subscribe((user: User) => {
            this.id = user.id;
            console.log('user', user);
            console.log(this.id);
        });
    }

    getData() {
        if (this.authService.getRole() === 'SUPERVISOR') {
            this.getUserData();
            this.userService.getAllBySupervisor(this.id).subscribe((data) => {
                this.dataSource = new MatTableDataSource<User>(data as User[]);
                this.dataSource.sort = this.sort;
            });
        } else {
            this.userService.getAll().subscribe((data) => {
                this.dataSource = new MatTableDataSource<User>(data as User[]);
                this.dataSource.sort = this.sort;
            });
        }
    }

    applyFilter(filterValue: string) {
        this.dataSource.filter = filterValue.trim().toLowerCase();
    }

    delete(user: User) {
        this.userService.delete(user.id).subscribe(
            () => {
                this.getData();
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
