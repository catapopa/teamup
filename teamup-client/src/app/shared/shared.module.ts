import { BasicUserInfoComponent } from './../components/basic-user-info/basic-user-info.component';
import { UserDetailsComponent } from '../components/user-details/user-details.component';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TopBarComponent } from './top-bar/top-bar.component';
import {
    MatIconModule, MatInputModule, MatFormFieldModule,
    MatAutocompleteModule, MatToolbarModule, MatButtonModule,
    MatTableModule, MatSelectModule, MatPaginatorModule, MatSortModule,
    MatDialogModule, MatMenuModule, MatTooltipModule, MatSnackBarModule, MatCardModule, MatDatepickerModule, MatNativeDateModule
} from '@angular/material';
import { ProfileComponent } from '../components/profile/profile.component';
import { HomeComponent } from '../components/home/home.component';
import { CompanyComponent } from '../components/company/company.component';
import { IndustryComponent } from '../components/industry/industry.component';
import { LocationComponent } from '../components/location/location.component';
import { TechAreaComponent } from '../components/tech-area/tech-area.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { UsersComponent } from '../components/users/users.component';
import { TranslateModule } from '@ngx-translate/core';
import { UserSeniorityComponent } from '../components/user-seniority/user-seniority.component';
import { TechnologyComponent } from '../components/technology/technology.component';
import { UserSkillLevelComponent } from '../components/user-skill-level/user-skill-level.component';
import { UserSkillComponent } from '../components/user-skill/user-skill.component';
import { UserExperienceComponent } from '../components/user-experience/user-experience.component';
import { ProjectComponent } from '../components/project/project.component';
import { ProjectExperienceComponent } from '../components/project-experience/project-experience.component';
import { TechnicalUserInfoComponent } from '../components/technical-user-info/technical-user-info.component';
import { PictureComponent } from '../components/picture/picture.component';
import {InviteComponent} from "../components/invite/invite.component";

@NgModule({
    declarations: [
        TopBarComponent,
        HomeComponent,
        ProfileComponent,
        CompanyComponent,
        IndustryComponent,
        LocationComponent,
        TechAreaComponent,
        UsersComponent,
        UserDetailsComponent,
        BasicUserInfoComponent,
        UserSeniorityComponent,
        TechnologyComponent,
        UserSkillLevelComponent,
        UserSkillComponent,
        UserExperienceComponent,
        ProjectComponent,
        ProjectExperienceComponent,
        TechnicalUserInfoComponent,
        PictureComponent,
        InviteComponent
    ],
    exports: [
        CommonModule,
        RouterModule,
        TopBarComponent,
        CompanyComponent
    ],
    imports: [
        CommonModule,
        RouterModule,
        FormsModule,
        ReactiveFormsModule,
        TranslateModule,
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
        MatAutocompleteModule,
        MatCardModule,
        MatSelectModule,
        MatDatepickerModule,
        MatNativeDateModule
    ],
    providers: [
        TopBarComponent
    ],
    entryComponents: [
        UserDetailsComponent
    ]
})
export class SharedModule {
}
