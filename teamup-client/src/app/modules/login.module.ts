import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatCardModule } from '@angular/material';
import { SharedModule } from '../shared/shared.module';
import { LoginComponent } from './login/login.component';


@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        MatCardModule,
        MatButtonModule
    ],
    declarations: [
        LoginComponent
    ]
})
export class LoginModule { }
