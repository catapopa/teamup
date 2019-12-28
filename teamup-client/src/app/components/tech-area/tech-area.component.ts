import {Component, forwardRef, OnInit} from '@angular/core';
import { TechAreaService } from "../../core/services/techArea/tech-area.service";
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";

@Component({
    selector: 'teamup-tech-area',
    templateUrl: './tech-area.component.html',
    styleUrls: ['./tech-area.component.scss'],
    providers: [
    {
        provide: NG_VALUE_ACCESSOR,
        useExisting: forwardRef(() => TechAreaComponent),
        multi: true
    }],
})
export class TechAreaComponent implements OnInit, ControlValueAccessor {

    techAreas: any;
    techAreaForm: FormGroup;

    constructor(private techAreaService: TechAreaService,formBuilder: FormBuilder) {
        this.techAreaForm = formBuilder.group({
            name: new FormControl('', [Validators.required])
        });
    }

    ngOnInit() {
        // this.techAreaService.getAll().subscribe((data) => {
        //     this.techAreas = data;
        // });
    }

    onTouched: any = () => { };

    writeValue(val: any): void {
        val && this.techAreaForm.setValue(val, {emitEvent:false});
    }
    registerOnChange(fn: any): void {
        this.techAreaForm.valueChanges.subscribe(fn)
    }
    registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }
    setDisabledState?(isDisabled: boolean): void {
        isDisabled ? this.techAreaForm.disable() : this.techAreaForm.enable();
    }

}
