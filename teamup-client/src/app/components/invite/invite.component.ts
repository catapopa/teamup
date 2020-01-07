import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {UserRole} from "../../shared/models/userRole";
import {User} from "../../shared/models/user";
import {UserService} from "../../core/services/user/user.service";


@Component({
    selector: 'teamup-invite',
    templateUrl: './invite.component.html',
    styleUrls: ['./invite.component.scss']
})
export class InviteComponent implements OnInit {
    form: FormGroup;
    roles: UserRole[] = [UserRole.ADMIN, UserRole.SUPERVISOR, UserRole.EMPLOYEE, UserRole.CUSTOMER];
    supervisors: User[] = [];
    email: string;
    role: UserRole;
    supervisor: string;
    username: string;

    constructor(private formBuilder: FormBuilder, private userService: UserService) {
        this.form = formBuilder.group({
            username: [],
            email: []
        });
    }

    ngOnInit() {
    }

    save() {
        this.email = this.form.get('email').value.toString();
        this.username = this.form.get('username').value.toString();

        let userToBeAdded: User = {
            id: null,
            username: this.username,
            password: null,
            email: this.email,
            firstName: null,
            lastName: null,
            birthDate: null,
            picture: null,
            language: null,
            role: this.role,
            seniority: null,
            location: null,
            company: null,
            skills: [],
            projectExperiences: []
        };

        console.log(userToBeAdded.role);
        console.log(userToBeAdded.email);
        console.log(userToBeAdded.username);


        this.userService.save(userToBeAdded).subscribe(() => {
            console.log("Added")
        });
    }
}
