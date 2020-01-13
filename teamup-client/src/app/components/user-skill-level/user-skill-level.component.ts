import {Component, forwardRef, OnInit} from '@angular/core';
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";
import {UserSkillLevel} from "../../shared/models/userSkillLevel";

@Component({
  selector: 'teamup-user-skill-level',
  templateUrl: './user-skill-level.component.html',
  styleUrls: ['./user-skill-level.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => UserSkillLevelComponent),
      multi: true
    }],
})
export class UserSkillLevelComponent implements OnInit, ControlValueAccessor {

  userSkillLevelForm: FormGroup;
  userSkillLevel = UserSkillLevel;
  keys = Object.keys(this.userSkillLevel);

  constructor(formBuilder: FormBuilder) {
    this.userSkillLevelForm = formBuilder.group({
      level: new FormControl(null, [Validators.required])
    });
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(val: any): void {
    if(val ===null){
      return;
    }
    this.userSkillLevelForm.setValue({
      level: val
    });
  }
  registerOnChange(fn: any): void {
    this.userSkillLevelForm.valueChanges.subscribe(fn)
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.userSkillLevelForm.disable() : this.userSkillLevelForm.enable();
  }
}
