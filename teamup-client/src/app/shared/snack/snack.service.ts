import { MatSnackBar, MatSnackBarConfig } from '@angular/material';
import SnackComponent from './snack.component';


export class ConfirmPromptService {

    private readonly successClassName: string = 'success-snackbar';
    private readonly warningClassName: string = 'warning-snackbar';
    private readonly errorClassName: string = 'error-snackbar';
    private readonly genericErrorMsgKey: string = 'snackMessages.genericError';

    constructor(private snackBar: MatSnackBar) { }

    commonConfig() {
        const config = new MatSnackBarConfig();
        config.verticalPosition = 'top';
        return config;
    }

    openSnack(messageKey: string, className: string, config: MatSnackBarConfig) {
        config.panelClass = [className];

        this.snackBar.openFromComponent(SnackComponent, config);
    }

    openSnackBar(messageKey: string) {
        const config = this.commonConfig();
        config.duration = 3000;
        config.data = { success: true };

        this.openSnack(messageKey, this.successClassName, config);
    }

    openSnackBarWarning(messageKey: string) {
        const config = this.commonConfig();
        config.data = { warning: true };

        this.openSnack(messageKey, this.warningClassName, config);
    }

    openSnackBarError(messageKey?: string) {
        const config = this.commonConfig();
        const errorMsgKey = messageKey || this.genericErrorMsgKey;
        config.data = { error: true };

        this.openSnack(errorMsgKey, this.errorClassName, config);
    }
}
