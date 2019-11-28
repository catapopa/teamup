import { MatSnackBar } from '@angular/material';
import { Component, HostListener, Inject } from '@angular/core';
import { MAT_SNACK_BAR_DATA } from '@angular/material';

@Component({
    selector: 'snack',
    templateUrl: './snack.component.html'
})
export default class SnackComponent {

    constructor(
        @Inject(MAT_SNACK_BAR_DATA) public data: any,
        private snackBar: MatSnackBar
    ) { }

    @HostListener('document:click', ['$event'])
    closeSnack(e: any) {
        if (e.target.closest('.snack-wrapper') && !e.target.classList.contains('close-icon')) {
            return;
        }
        this.snackBar.dismiss();
    }
}
