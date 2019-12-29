import { Component, OnInit, forwardRef } from '@angular/core';
import { CompanyService } from 'src/app/core/services/company/company.service';
import { ControlValueAccessor, FormGroup, NG_VALUE_ACCESSOR, Validators, FormBuilder, FormControl } from '@angular/forms';
import {Company} from "../../shared/models/company";

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
      company: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
    this.companyService.getAll().subscribe((data) => {
      this.companies = data;
    });
  }

  displayFn(company?: Company): string | undefined {
    return company ? company.name : undefined;
  }

  onTouched: any = () => { };

  writeValue(val: any): void {
    val && this.companyForm.setValue(val, {emitEvent:false});
  }
  registerOnChange(fn: any): void {
    this.companyForm.valueChanges
       //.pipe(map(company=>{}))
        .subscribe(fn)
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.companyForm.disable() : this.companyForm.enable();
  }

}
