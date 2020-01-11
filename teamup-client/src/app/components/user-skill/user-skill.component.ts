import {Component, forwardRef, OnInit} from '@angular/core';
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";
import {UserSkill} from "../../shared/models/userSkill";

@Component({
  selector: 'teamup-user-skill',
  templateUrl: './user-skill.component.html',
  styleUrls: ['./user-skill.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => UserSkillComponent),
      multi: true
    }],
})
export class UserSkillComponent implements OnInit, ControlValueAccessor {

  userSkillForm: FormGroup;

  constructor(formBuilder: FormBuilder) {
    this.userSkillForm = formBuilder.group({
      id: new FormControl(null),
      technology: new FormControl(null, [Validators.required]),
      level: new FormControl(null, [Validators.required])
    });
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(skill: UserSkill): void {
    if (skill === null) {
      return;
    }
    this.userSkillForm.setValue({
      id: skill.id,
      technology: skill.technology,
      level: skill.level
    });
  }
  registerOnChange(fn: any): void {
    this.userSkillForm.valueChanges.subscribe(fn)
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.userSkillForm.disable() : this.userSkillForm.enable();
  }
}
