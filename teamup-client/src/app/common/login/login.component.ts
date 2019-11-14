import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../core/services/user/user.service';
import {UserLogin} from '../../core/models/userLogin';
import {Token} from '../../core/models/token';
import {LoginValidators} from './login.validators';
import {AuthService} from '../../core/services/auth/auth.service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    providers: [UserService]
})
export class LoginComponent implements OnInit {

    form: FormGroup;
    userLogin: UserLogin;
    token: Token;
    username: string;
    password: string;


    constructor(private router: Router, private userService: UserService, private fb: FormBuilder,
                private authService: AuthService) {
        this.form = fb.group({
            username: [null, [Validators.required, LoginValidators.cannotContainSpace,
                LoginValidators.cannotContainUpperCaseLetter]],
            password: [null, [Validators.required]]
        });
    }

    ngOnInit() {
        // if the user is already logged in, it navigates to dashboard.
        if (localStorage.getItem('token')) {
            this.router.navigate(['dashboard']);
        }
    }

    /**
     * Log in the user with a username and a password.
     *
     * */
    login() {
        this.username = this.form.get('username').value.toString();
        this.password = this.form.get('password').value.toString();
        this.userLogin = {username: this.username, password: this.password};
        this.authService.login(this.userLogin);
    }
}
