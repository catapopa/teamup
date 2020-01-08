import {Component, forwardRef, OnInit} from '@angular/core';
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";
import {Project} from "../../shared/models/project";
import {ProjectExperience} from "../../shared/models/projectExperience";

@Component({
  selector: 'teamup-project-experience',
  templateUrl: './project-experience.component.html',
  styleUrls: ['./project-experience.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => ProjectExperienceComponent),
      multi: true
    }],
})
export class ProjectExperienceComponent implements OnInit,ControlValueAccessor {

  projectExperienceForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.projectExperienceForm = formBuilder.group({
      project: new FormControl('', [Validators.required]),
      startDate: new FormControl('', [Validators.required]),
      endDate: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required])
    })
  }

  ngOnInit() {
  }

  onTouched: any = () => { };

  writeValue(projectExp: ProjectExperience): void {
    if(projectExp === null){
      return;
    }
    console.log(projectExp);
  }
  registerOnChange(fn: any): void {
    this.projectExperienceForm.valueChanges.subscribe(fn)
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.projectExperienceForm.disable() : this.projectExperienceForm.enable();
  }

}
