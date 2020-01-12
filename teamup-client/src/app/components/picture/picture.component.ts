import { Component, forwardRef, OnInit } from '@angular/core';
import { ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators } from '@angular/forms';

@Component({
    selector: 'teamup-picture',
    templateUrl: './picture.component.html',
    styleUrls: ['./picture.component.scss'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => PictureComponent),
            multi: true
        }],
})
export class PictureComponent implements OnInit, ControlValueAccessor {

    pictureForm: FormGroup;

    constructor(formBuilder: FormBuilder) {
        this.pictureForm = formBuilder.group({
            blob: new FormControl('', [Validators.required])
        });
    }

    ngOnInit() {
    }

    processFile(imageInput: any) {
        const file: File = imageInput.files[0];
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
            this.pictureForm.get('blob').setValue(reader.result.toString());
        };
    }

    onTouched: any = () => { };

    writeValue(val: any): void {
        if(val == null){
            return;
        }
        this.pictureForm.setValue({
            blob: val
        });
    }
    registerOnChange(fn: any): void {
        this.pictureForm.valueChanges.subscribe(fn);
    }
    registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }
    setDisabledState?(isDisabled: boolean): void {
        isDisabled ? this.pictureForm.disable() : this.pictureForm.enable();
    }

}
