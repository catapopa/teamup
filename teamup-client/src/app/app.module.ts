import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './common/login/login.component';
import { DashboardComponent } from './common/dashboard/dashboard.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from './core/interceptors/auth.interceptor';
import { ProfileComponent } from './core/components/profile/profile.component';
import { CompanyComponent } from './core/components/company/company.component';
import { LocationComponent } from './core/components/location/location.component';
import { IndustryComponent } from './core/components/industry/industry.component';
import { TechAreaComponent } from './core/components/tech-area/tech-area.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {DropdownModule} from "primeng/primeng";
import {InputTextModule} from 'primeng/inputtext';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ProfileComponent,
    CompanyComponent,
    LocationComponent,
    IndustryComponent,
    TechAreaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DropdownModule,
    InputTextModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
  }

  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
