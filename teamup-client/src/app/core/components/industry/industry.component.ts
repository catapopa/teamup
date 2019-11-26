import { Component, OnInit } from '@angular/core';
import {IndustryService} from "../../services/industry/industry.service";
import {Industry} from "../../models/industry";

@Component({
  selector: 'app-industry',
  templateUrl: './industry.component.html',
  styleUrls: ['./industry.component.scss']
})
export class IndustryComponent implements OnInit {

  industries: Industry[];

  constructor(private industryService: IndustryService) { }

  ngOnInit() {
    this.industryService.getAll().subscribe(
        data=>{this.industries = data},
        error1 => {console.log(error1)}
    );
  }

}
