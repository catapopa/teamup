import {Component, forwardRef, OnInit} from '@angular/core';
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";

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
      techName: new FormControl('', [Validators.required]),
      techArea: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(val: any): void {
    val && this.technologyForm.setValue(val, {emitEvent:false});
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
