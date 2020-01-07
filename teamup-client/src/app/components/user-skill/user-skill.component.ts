import {Component, forwardRef, OnInit} from '@angular/core';
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";

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
      technology: new FormControl('', [Validators.required]),
      skillLevel: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(val: any): void {
    val && this.userSkillForm.setValue(val, {emitEvent:false});
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
