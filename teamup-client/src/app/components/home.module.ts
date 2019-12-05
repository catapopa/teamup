import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileModule } from './profile.module';
import { HomeComponent } from './home/home.component';
import { HomeRoutingModule } from '../components/home-routing.module';


@NgModule({
    imports: [
        CommonModule,
        HomeRoutingModule,
        ProfileModule
    ],
    declarations: [
        HomeComponent
    ]
})
export class HomeModule { }
