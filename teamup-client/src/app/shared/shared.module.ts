import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SnackComponent } from './snack/snack.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import {
  MatIconModule, MatInputModule, MatFormFieldModule,
  MatAutocompleteModule, MatToolbarModule, MatButtonModule,
  MatTableModule, MatSelectModule, MatPaginatorModule, MatSortModule,
  MatDialogModule, MatMenuModule, MatTooltipModule, MatSnackBarModule
} from '@angular/material';
import { ProfileComponent } from '../components/profile/profile.component';
import { HomeComponent } from '../components/home/home.component';
import { CompanyComponent } from '../components/company/company.component';
import { IndustryComponent } from '../components/industry/industry.component';
import { LocationComponent } from '../components/location/location.component';
import { TechAreaComponent } from '../components/tech-area/tech-area.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
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
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatSelectModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    MatSortModule,
    MatIconModule,
    MatDialogModule,
    MatButtonModule,
    MatMenuModule,
    MatTooltipModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatAutocompleteModule
  ],
  providers: [
    TopBarComponent
  ]
})
export class SharedModule { }
