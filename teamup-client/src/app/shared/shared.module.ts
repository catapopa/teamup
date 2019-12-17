import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import SnackComponent from './snack/snack.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import {
  MatIconModule, MatInputModule, MatFormFieldModule, MatOptionModule,
  MatAutocompleteModule, MatToolbarModule, MatButton, MatButtonModule
} from '@angular/material';
import { ProfileComponent } from '../components/profile/profile.component';
import { HomeComponent } from '../components/home/home.component';
import { CompanyComponent } from '../components/company/company.component';
import { IndustryComponent } from '../components/industry/industry.component';
import { LocationComponent } from '../components/location/location.component';
import { TechAreaComponent } from '../components/tech-area/tech-area.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UsersComponent } from '../components/users/users.component';

@NgModule({
  declarations: [
    SnackComponent,
    TopBarComponent,
    HomeComponent,
    ProfileComponent,
    CompanyComponent,
    IndustryComponent,
    LocationComponent,
    TechAreaComponent,
    UsersComponent
  ],
  exports: [
    CommonModule,
    RouterModule,
    TopBarComponent,
    CompanyComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatOptionModule,
    MatAutocompleteModule,
    MatToolbarModule,
    MatButtonModule
  ],
  providers: [
    TopBarComponent
  ]
})
export class SharedModule { }
