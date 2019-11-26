import { Component, OnInit } from '@angular/core';
import {CompanyService} from "../../services/company/company.service";
import {Company} from "../../models/company";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {

  companies: Company[];

  constructor(private companyService: CompanyService) {

  }

  ngOnInit() {
    this.companyService.getAll().subscribe(
        data=>{this.companies = data},
        error1 => {console.log(error1)}
    );
  }


}
