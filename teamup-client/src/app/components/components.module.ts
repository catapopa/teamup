import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CompanyComponent } from './company/company.component';
import { IndustryComponent } from './industry/industry.component';
import { LocationComponent } from './location/location.component';
import { ProfileComponent } from './profile/profile.component';
import { TechAreaComponent } from './tech-area/tech-area.component';

@NgModule({
    declarations: [
        CompanyComponent,
        IndustryComponent,
        LocationComponent,
        ProfileComponent,
        TechAreaComponent
    ],
    exports: [
        CommonModule,
        RouterModule,
    ],
    imports: [
        CommonModule,
        RouterModule,
    ],
    providers: [
    ]
})
export class ComponentsModule { }
