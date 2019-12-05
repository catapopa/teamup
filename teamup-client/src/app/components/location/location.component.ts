import { Component, OnInit } from '@angular/core';
import { LocationService } from "../../core/services/location/location.service";

@Component({
    selector: 'teamup-location',
    templateUrl: './location.component.html',
    styleUrls: ['./location.component.scss']
})
export class LocationComponent implements OnInit {

    locations: any;

    constructor(private locationService: LocationService) { }

    ngOnInit() {
        this.locationService.getAll().subscribe((data) => {
            this.locations = data;
        });
    }

}
