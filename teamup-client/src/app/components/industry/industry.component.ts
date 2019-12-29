import { Component, OnInit } from '@angular/core';
import { IndustryService } from '../../core/services/industry/industry.service';

@Component({
  selector: 'teamup-industry',
  templateUrl: './industry.component.html',
  styleUrls: ['./industry.component.scss']
})
export class IndustryComponent implements OnInit {

  industries: any;

  constructor(private industryService: IndustryService) { }

  ngOnInit() {
    this.industryService.getAll().subscribe((data) => {
      this.industries = data;
    });
  }

}
