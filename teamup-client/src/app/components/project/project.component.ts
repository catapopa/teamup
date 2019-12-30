import {Component, forwardRef, OnInit} from '@angular/core';
import {
  ControlValueAccessor,
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  NG_VALUE_ACCESSOR,
  Validators
} from "@angular/forms";


@Component({
  selector: 'teamup-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => ProjectComponent),
      multi: true
    }],
})
export class ProjectComponent implements OnInit,ControlValueAccessor {

  projectForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.projectForm = formBuilder.group({
      name: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      industry: new FormControl('', [Validators.required]),
      company: new FormControl('', [Validators.required]),
      userExperience: this.formBuilder.array([])
    });
    this.addUserExperience();
  }

  addUserExperience(){
    const userExp = this.projectForm.get('userExperience') as FormArray;
    const control = new FormControl(null,[Validators.required]);
    userExp.push(control);
  }

  removeUserExperience(i: number){
    const userExp = this.projectForm.get('userExperience') as FormArray;
    userExp.removeAt(i);
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(val: any): void {
    val && this.projectForm.setValue(val, {emitEvent:false});
  }
  registerOnChange(fn: any): void {
    this.projectForm.valueChanges.subscribe(fn)
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.projectForm.disable() : this.projectForm.enable();
  }

}
