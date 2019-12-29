import { Component, OnInit } from '@angular/core';
import { LocationService } from "../../core/services/location/location.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
    selector: 'teamup-location',
    templateUrl: './location.component.html',
    styleUrls: ['./location.component.scss']
})
export class LocationComponent implements OnInit {

    locations: any;
    locationForm: FormGroup;
    locationsAsString: string[];

    constructor(private locationService: LocationService, private formBuilder:FormBuilder) {
        this.locationForm = formBuilder.group({
            location: new FormControl('', [Validators.required])
        })
    }

    ngOnInit() {
        this.locationService.getAll().subscribe((data) => {
            this.locations = data;
        });
        this.locationsAsString = this.locations.map(location=>location.country+" , "+location.state+" , "+location.city);
        console.log(this.locationsAsString)
    }

}
