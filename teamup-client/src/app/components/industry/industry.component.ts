import {Component, forwardRef, OnInit} from '@angular/core';
import { IndustryService } from "../../core/services/industry/industry.service";
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";
import {Industry} from "../../shared/models/industry";

@Component({
  selector: 'teamup-industry',
  templateUrl: './industry.component.html',
  styleUrls: ['./industry.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => IndustryComponent),
      multi: true
    }],
})
export class IndustryComponent implements OnInit, ControlValueAccessor {

  industries: any;
  industryForm: FormGroup;

  constructor(private industryService: IndustryService,formBuilder: FormBuilder) {
    this.industryForm = formBuilder.group({
      industry: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
    this.industryService.getAll().subscribe((data) => {
      this.industries = data;
    });
  }

  displayFn(industry?: Industry): string | undefined {
    return industry ? industry.name : undefined;
  }

  onTouched: any = () => { };

  writeValue(val: any): void {
    val && this.industryForm.setValue(val, {emitEvent:false});
  }
  registerOnChange(fn: any): void {
    this.industryForm.valueChanges.subscribe(fn)
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.industryForm.disable() : this.industryForm.enable();
  }

}
