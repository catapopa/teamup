import { Component, OnInit } from '@angular/core';
import { TechAreaService } from "../../core/services/techArea/tech-area.service";

@Component({
    selector: 'teamup-tech-area',
    templateUrl: './tech-area.component.html',
    styleUrls: ['./tech-area.component.scss']
})
export class TechAreaComponent implements OnInit {

    techAreas: any;

    constructor(private techAreaService: TechAreaService) { }

    ngOnInit() {
        this.techAreaService.getAll().subscribe((data) => {
            this.techAreas = data;
        });
    }

}
