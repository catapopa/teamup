import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import SnackComponent from './snack/snack.component';
import { HttpService } from './http.service';

@NgModule({
  declarations: [
    SnackComponent
  ],
  exports: [
    CommonModule,
    RouterModule
  ],
  imports: [
    CommonModule,
    RouterModule,
  ],
  providers: [
    HttpService
  ]
})
export class SharedModule { }
