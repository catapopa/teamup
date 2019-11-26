import { Component, OnInit } from '@angular/core';
import {Location} from "../../models/location";
import {LocationService} from "../../services/location/location.service";

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.scss']
})
export class LocationComponent implements OnInit {

  locations: Location[];

  constructor(private locationService: LocationService) { }

  ngOnInit() {
    this.locationService.getAll().subscribe(
        data=>{this.locations = data},
        error1 => {console.log(error1)}
    );
  }

}
