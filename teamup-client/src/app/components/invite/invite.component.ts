import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../core/services/user/user.service";
import {User} from "../../shared/models/user";
import {MatSnackBar} from "@angular/material/snack-bar";
import {UserRole} from "../../shared/models/userRole";


@Component({
    selector: 'teamup-invite',
    templateUrl: './invite.component.html',
    styleUrls: ['./invite.component.scss']
})

export class InviteComponent {
    invitationForm: FormGroup;
    selectedRole = UserRole.EMPLOYEE;
    key = Object.keys;
    roles = UserRole;

    constructor(private formBuilder: FormBuilder, private userService: UserService, private snackbar: MatSnackBar) {
        this.invitationForm = formBuilder.group({
            username: new FormControl('', [Validators.required]),
            email: new FormControl('', [Validators.required]),
            password: new FormControl('', [Validators.required]),
            role: new FormControl('', [Validators.required]),
            location: new FormControl('', [Validators.required]),
            company: new FormControl('', [Validators.required]),
        });
    }

    onSubmit() {
        const user: User = {
            id: 0,
            username: this.invitationForm.get('username').value,
            password: this.invitationForm.get('password').value,
            email: this.invitationForm.get('email').value,
            role: this.invitationForm.get('role').value,
            location: this.invitationForm.get('location').value.location,
            company: {
                id: this.invitationForm.get('company').value.company.id ? this.invitationForm.get('company').value.company.id : 0,
                name: this.invitationForm.get('company').value.company.name ? this.invitationForm.get('company').value.company.name : this.invitationForm.get('company').value.company
            },
            firstName: null,
            lastName: null,
            birthDate: null,
            picture: null,
            language: null, // todo
            seniority: null,
            skills: null,
            projectExperiences: null
        };
        console.log(user);

        this.userService.invite(user).subscribe(
            () => {
                this.snackbar.open('Invitation sent.');
            },
            () => {
                this.snackbar.open('Error occurred.');
            }
        );
    }
}
