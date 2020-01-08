import {Component, forwardRef, OnInit} from '@angular/core';
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";
import {UserSkill} from "../../shared/models/userSkill";
import {Technology} from "../../shared/models/technology";

@Component({
  selector: 'teamup-technology',
  templateUrl: './technology.component.html',
  styleUrls: ['./technology.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => TechnologyComponent),
      multi: true
    }],
})
export class TechnologyComponent implements OnInit, ControlValueAccessor {

  technologyForm: FormGroup;

  constructor(formBuilder: FormBuilder) {
    this.technologyForm = formBuilder.group({
      id: new FormControl(null),
      name: new FormControl(null, [Validators.required]),
      area: new FormControl(null, [Validators.required])
    });
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(technology: Technology): void {
    if(technology === null){
      return;
    }
    this.technologyForm.setValue({
      id: technology.id,
      name: technology.name,
      area: technology.area
    })

  }
  registerOnChange(fn: any): void {
    this.technologyForm.valueChanges.subscribe(fn)
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.technologyForm.disable() : this.technologyForm.enable();
  }

}
