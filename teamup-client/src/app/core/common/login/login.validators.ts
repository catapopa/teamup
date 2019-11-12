import {AbstractControl, ValidationErrors} from '@angular/forms';

export class LoginValidators {

    /**
     * Introduced username should not contain
     * any empty space.
     * */
    static cannotContainSpace(control: AbstractControl): ValidationErrors | null {
        const value: string = control.value;
        if (value && value.indexOf(' ') >= 0) {
            return {cannotContainSpace: true};
        }
    }

    /**
     * Introduced username should not contain
     * any uppercase letters.
     * */
    static cannotContainUpperCaseLetter(control: AbstractControl): ValidationErrors | null {
        const value: string = control.value;
        if (value) {
            for (let i = 0; i < value.length; i++) {
                if (value[i].match(/[A-Z]/)) {
                    return {cannotContainUpperCaseLetter: true};
                }
            }
        }
    }
}
