import { Component, OnInit } from '@angular/core';
import {TechnologyArea} from "../../models/technologyArea";
import {TechAreaService} from "../../services/techArea/tech-area.service";

@Component({
  selector: 'app-tech-area',
  templateUrl: './tech-area.component.html',
  styleUrls: ['./tech-area.component.scss']
})
export class TechAreaComponent implements OnInit {

  techAreas : TechnologyArea[];

  constructor(private techAreaService: TechAreaService) { }

  ngOnInit() {
    this.techAreaService.getAll().subscribe(
        data=>{this.techAreas = data},
        error1 => {console.log(error1)}
    );
  }

}
