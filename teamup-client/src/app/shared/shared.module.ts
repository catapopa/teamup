import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import SnackComponent from './snack/snack.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { MatIconModule } from '@angular/material';

@NgModule({
  declarations: [
    SnackComponent,
    TopBarComponent
  ],
  exports: [
    CommonModule,
    RouterModule,
    TopBarComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule
  ],
  providers: [
    TopBarComponent
  ]
})
export class SharedModule { }
