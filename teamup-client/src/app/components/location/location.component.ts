import { Component, forwardRef, OnInit } from '@angular/core';
import { ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators } from '@angular/forms';
import { Location } from "../../shared/models/location";
import {LocationService} from "../../core/services/location/location.service";
import {take} from "rxjs/operators";

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

    locationForm: FormGroup;
    locationsPromise: Promise<Location[]>;

    constructor(private locationService: LocationService, private formBuilder: FormBuilder) {
        this.locationForm = formBuilder.group({
            location: new FormControl(null, [Validators.required])
        });
    }

    ngOnInit() {
        this.locationsPromise = this.locationService.getAll().pipe(take(1)).toPromise() as Promise<Location[]>;

    }

    onTouched: any = () => { };

    async writeValue(val: Location) {
        if(val=== null){
            return;
        }
        const locations = await this.locationsPromise;
        const foundLocation = locations.find(x=>x.id===val.id);
        if (!foundLocation){
            return;
        }
        this.locationForm.setValue({
            location: foundLocation
        });
    }
    registerOnChange(fn: any): void {
        this.locationForm.valueChanges.subscribe(fn);
    }
    registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }
    setDisabledState?(isDisabled: boolean): void {
        isDisabled ? this.locationForm.disable() : this.locationForm.enable();
    }

}
