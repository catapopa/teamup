import {Component, forwardRef, OnInit} from '@angular/core';
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";

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
      fullName: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      startDate: new FormControl('', [Validators.required]),
      endDate: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required])
    })
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(val: any): void {
    val && this.userExperienceForm.setValue(val, {emitEvent:false});
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
