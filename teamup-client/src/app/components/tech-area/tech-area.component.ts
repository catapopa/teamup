import { Component, forwardRef, OnInit } from '@angular/core';
import { TechAreaService } from '../../core/services/techArea/tech-area.service';
import { ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators } from '@angular/forms';
import { TechnologyArea } from '../../shared/models/technologyArea';

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

    constructor(private techAreaService: TechAreaService, formBuilder: FormBuilder) {
        this.techAreaForm = formBuilder.group({
            area: new FormControl(null, [Validators.required])
        });
    }

    ngOnInit() {
        this.techAreaService.getAll().subscribe((data) => {
            this.techAreas = data;
        });
    }

    displayFn(techArea?: TechnologyArea): string | undefined {
        return techArea ? techArea.name : undefined;
    }

    onTouched: any = () => { };

    writeValue(val: any): void {
        if (val===null){
            return;
        }
        this.techAreaForm.setValue({
            area:val
        });
    }
    registerOnChange(fn: any): void {
        this.techAreaForm.valueChanges.subscribe(fn);
    }
    registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }
    setDisabledState?(isDisabled: boolean): void {
        isDisabled ? this.techAreaForm.disable() : this.techAreaForm.enable();
    }

}
