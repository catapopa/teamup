import {Component, forwardRef, OnInit} from '@angular/core';
import { LocationService } from "../../core/services/location/location.service";
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";

@Component({
    selector: 'teamup-location',
    templateUrl: './location.component.html',
    styleUrls: ['./location.component.scss'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => LocationComponent),
            multi: true
        }],
})
export class LocationComponent implements OnInit, ControlValueAccessor {

    locations: Location[];
    locationForm: FormGroup;

    constructor(private locationService: LocationService, private formBuilder:FormBuilder) {
        this.locationForm = formBuilder.group({
            location: new FormControl('', [Validators.required])
        })
    }

    ngOnInit() {
        this.locationService.getAll().subscribe((data) => {
            this.locations = data as Location[];
        });

    }

    onTouched: any = () => { };

    writeValue(val: any): void {
        val && this.locationForm.setValue(val, {emitEvent:false});
    }
    registerOnChange(fn: any): void {
        this.locationForm.valueChanges.subscribe(fn)
    }
    registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }
    setDisabledState?(isDisabled: boolean): void {
        isDisabled ? this.locationForm.disable() : this.locationForm.enable();
    }

}
