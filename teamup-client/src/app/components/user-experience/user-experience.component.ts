import {Component, forwardRef, OnInit} from '@angular/core';
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";
import {UserExperience} from "../../shared/models/userExperience";

@Component({
  selector: 'teamup-user-experience',
  templateUrl: './user-experience.component.html',
  styleUrls: ['./user-experience.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => UserExperienceComponent),
      multi: true
    }],
})
export class UserExperienceComponent implements OnInit, ControlValueAccessor {

  userExperienceForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.userExperienceForm = formBuilder.group({
      fullName: new FormControl(null, [Validators.required]),
      email: new FormControl(null, [Validators.required]),
      startDate: new FormControl(null, [Validators.required]),
      endDate: new FormControl(null, [Validators.required]),
      description: new FormControl(null, [Validators.required])
    })
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(userExp: UserExperience): void {
    if(userExp === null){
      return;
    }
    this.userExperienceForm.setValue({
      fullName: userExp.fullName,
      email: userExp.email,
      startDate: userExp.startDate,
      endDate:userExp.endDate,
      description: userExp.description
    })
  }
  registerOnChange(fn: any): void {
    this.userExperienceForm.valueChanges.subscribe(fn)
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.userExperienceForm.disable() : this.userExperienceForm.enable();
  }

}
