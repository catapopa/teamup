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
  selector: 'teamup-technical-user-info',
  templateUrl: './technical-user-info.component.html',
  styleUrls: ['./technical-user-info.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => TechnicalUserInfoComponent),
      multi: true
    }],
})
export class TechnicalUserInfoComponent implements OnInit, ControlValueAccessor {

  technicalInfoForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.technicalInfoForm = formBuilder.group({
      location: new FormControl('', [Validators.required]),
      seniority: new FormControl('', [Validators.required]),
      company: new FormControl('', [Validators.required]),
      skills: this.formBuilder.array([]),
      projectExperiences: this.formBuilder.array([])
    });
    this.addSkill();
    this.addProjectExperience();
  }

  addSkill(){
    const skills = this.technicalInfoForm.get('skills') as FormArray;
    const control = new FormControl(null,[Validators.required]);
    skills.push(control);
  }

  removeSkill(i: number){
    const skills = this.technicalInfoForm.get('skills') as FormArray;
    skills.removeAt(i);
  }

  addProjectExperience(){
    const projectExp = this.technicalInfoForm.get('projectExperiences') as FormArray;
    const control = new FormControl(null,[Validators.required]);
    projectExp.push(control);
  }

  removeProjectExperience(i: number) {
    const projectExp = this.technicalInfoForm.get('projectExperiences') as FormArray;
    projectExp.removeAt(i);
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(val: any): void {
    val && this.technicalInfoForm.setValue(val, {emitEvent:false});
  }
  registerOnChange(fn: any): void {
    this.technicalInfoForm.valueChanges.subscribe(fn)
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.technicalInfoForm.disable() : this.technicalInfoForm.enable();
  }


}
