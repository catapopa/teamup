import {Component, ViewChild} from '@angular/core';
import {MatSort, MatTableDataSource, MatDialog} from '@angular/material';
import {User} from 'src/app/shared/models/user';
import {UserService} from 'src/app/core/services/user/user.service';
import {UserDetailsComponent} from '../user-details/user-details.component';

@Component({
    selector: 'teamup-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.scss']
})
export class UsersComponent {

    @ViewChild(MatSort, {static: true}) sort: MatSort;

    dataSource: MatTableDataSource<User>;
    displayedColumns: string[] = ['firstName', 'lastName', 'email', 'menu'];

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

    getDetails(element) {
        console.log(element);
        this.userDetails.open(UserDetailsComponent, {
            data: {
                id: element.id,
                firstName: element.firstName,
                lastName: element.lastName,
                email: element.email,
                birthDate: element.birthDate,
                seniority: element.seniority,
                company: element.company.name,
                skills: element.skills,
                location: element.location,
                projectExperiences: element.projectExperiences
            }
        }).afterClosed().subscribe(() => {
            this.getData();
        });
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
}
