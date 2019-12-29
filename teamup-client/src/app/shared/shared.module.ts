import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SnackComponent } from './snack/snack.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import {
  MatIconModule,
  MatInputModule,
  MatFormFieldModule,
  MatOptionModule,
  MatAutocompleteModule,
  MatToolbarModule,
  MatButtonModule,
  MatSelectModule,
  MatDatepickerModule,
  MatNativeDateModule
  MatIconModule, MatInputModule, MatFormFieldModule,
  MatAutocompleteModule, MatToolbarModule, MatButtonModule,
  MatTableModule, MatSelectModule, MatPaginatorModule, MatSortModule,
  MatDialogModule, MatMenuModule, MatTooltipModule, MatSnackBarModule, MatCardModule
} from '@angular/material';
import { ProfileComponent } from '../components/profile/profile.component';
import { HomeComponent } from '../components/home/home.component';
import { CompanyComponent } from '../components/company/company.component';
import { IndustryComponent } from '../components/industry/industry.component';
import { LocationComponent } from '../components/location/location.component';
import { TechAreaComponent } from '../components/tech-area/tech-area.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { UsersComponent } from '../components/users/users.component';
import { LoginComponent } from '../components/login/login.component';
import {UserSeniorityComponent} from "../components/user-seniority/user-seniority.component";
import {TechnologyComponent} from "../components/technology/technology.component";
import {UserSkillLevelComponent} from "../components/user-skill-level/user-skill-level.component";
import {UserSkillComponent} from "../components/user-skill/user-skill.component";
import {UserExperienceComponent} from "../components/user-experience/user-experience.component";
import {ProjectComponent} from "../components/project/project.component";
import {ProjectExperienceComponent} from "../components/project-experience/project-experience.component";
import {BasicUserInfoComponent} from "../components/basic-user-info/basic-user-info.component";
import {TechnicalUserInfoComponent} from "../components/technical-user-info/technical-user-info.component";
import {PictureComponent} from "../components/picture/picture.component";

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
    UsersComponent,
    UserSeniorityComponent,
    TechnologyComponent,
    UserSkillLevelComponent,
    UserSkillComponent,
    UserExperienceComponent,
    ProjectComponent,
    ProjectExperienceComponent,
    BasicUserInfoComponent,
    TechnicalUserInfoComponent,
    PictureComponent,

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
    // Mat
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
    MatButtonModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule

    MatAutocompleteModule,
    MatCardModule
  ],
  providers: [
    TopBarComponent
  ]
})
export class SharedModule { }
