import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import SnackComponent from './snack/snack.component';
import { TopBarComponent } from './top-bar/top-bar.component';

@NgModule({
  declarations: [
    SnackComponent,
    TopBarComponent
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
  ]
})
export class SharedModule { }
