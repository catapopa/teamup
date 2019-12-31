import {Component, forwardRef, OnInit} from '@angular/core';
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";
import {UserSeniority} from "../../shared/models/userSeniority";

@Component({
  selector: 'teamup-user-seniority',
  templateUrl: './user-seniority.component.html',
  styleUrls: ['./user-seniority.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => UserSeniorityComponent),
      multi: true
    }],
})
export class UserSeniorityComponent implements OnInit, ControlValueAccessor {

  userSeniorityForm: FormGroup;
  userSeniority = UserSeniority;
  keys = Object.keys(this.userSeniority);

  constructor(formBuilder: FormBuilder) {
    this.userSeniorityForm = formBuilder.group({
      seniority: new FormControl('',[Validators.required])
    });
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(val: any): void {
    val && this.userSeniorityForm.setValue(val, {emitEvent:false});
  }
  registerOnChange(fn: any): void {
    this.userSeniorityForm.valueChanges.subscribe(fn)
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.userSeniorityForm.disable() : this.userSeniorityForm.enable();
  }

}
