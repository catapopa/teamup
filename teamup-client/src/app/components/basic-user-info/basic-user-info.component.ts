import {Component, forwardRef, OnInit} from '@angular/core';
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";
import {UserLanguage} from "../../shared/models/userLanguage";

@Component({
  selector: 'teamup-basic-user-info',
  templateUrl: './basic-user-info.component.html',
  styleUrls: ['./basic-user-info.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => BasicUserInfoComponent),
      multi: true
    }],
})
export class BasicUserInfoComponent implements OnInit, ControlValueAccessor {

  basicInfoForm: FormGroup;
  language = UserLanguage;
  keys = Object.keys(this.language);

  constructor(private formBuilder: FormBuilder) {
    this.basicInfoForm = formBuilder.group({
      firstName: new FormControl(null, [Validators.required]),
      lastName: new FormControl(null, [Validators.required]),
      email: new FormControl(null, [Validators.required]),
      birthDate: new FormControl(null, [Validators.required]),
      picture: new FormControl(null, [Validators.required]),
      language: new FormControl(null, [Validators.required]),
    })
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(val: any): void {
    val && this.basicInfoForm.setValue(val, {emitEvent:false});
  }
  registerOnChange(fn: any): void {
    this.basicInfoForm.valueChanges.subscribe(fn)
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.basicInfoForm.disable() : this.basicInfoForm.enable();
  }


}
