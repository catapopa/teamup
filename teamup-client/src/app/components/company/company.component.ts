import { Component, OnInit, forwardRef } from '@angular/core';
import { CompanyService } from 'src/app/core/services/company/company.service';
import { ControlValueAccessor, FormGroup, NG_VALUE_ACCESSOR, Validators, FormBuilder, FormControl } from '@angular/forms';
import { ProfileComponent } from '../profile/profile.component';

@Component({
  selector: 'teamup-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => CompanyComponent),
      multi: true
    }],
})
export class CompanyComponent implements OnInit, ControlValueAccessor {

  companies: any;
  companyForm: FormGroup;

  constructor(private companyService: CompanyService, formBuilder: FormBuilder) {
    this.companyForm = formBuilder.group({
      name: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
    this.companyService.getAll().subscribe((data) => {
      this.companies = data;
      console.log(this.companies);
    });
  }

  writeValue(obj: any): void {
    throw new Error('Method not implemented.');
  }
  registerOnChange(fn: any): void {
    throw new Error('Method not implemented.');
  }
  registerOnTouched(fn: any): void {
    throw new Error('Method not implemented.');
  }
  setDisabledState?(isDisabled: boolean): void {
    throw new Error('Method not implemented.');
  }
}
