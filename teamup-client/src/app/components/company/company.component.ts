import { Component, OnInit } from '@angular/core';
import { CompanyService } from "../../core/services/company/company.service";

@Component({
  selector: 'teamup-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {

  companies: any;

  constructor(private companyService: CompanyService) { }

  ngOnInit() {
    this.companyService.getAll().subscribe((data) => {
      this.companies = data;
    });
  }


}
