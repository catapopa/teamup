import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/services/user/user.service';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { User } from '../../shared/models/user';
import { UserSkill } from '../../shared/models/userSkill';
import { ProjectExperience } from '../../shared/models/projectExperience';
import { Technology } from '../../shared/models/technology';
import { Project } from '../../shared/models/project';
import { UserExperience } from '../../shared/models/userExperience';
import {AuthService} from "../../core/authentication/auth.service";
import {combineAll} from "rxjs/operators";

@Component({
  selector: 'teamup-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  currentUser: User;
  profileForm: FormGroup;

  constructor(private userService: UserService, formBuilder: FormBuilder, private authService: AuthService) {
    this.profileForm = formBuilder.group({
      basicInfo: new FormControl(null, [Validators.required]),
      technicalInfo: new FormControl(null, [Validators.required]),
    });
  }

  ngOnInit() {
    const id = this.authService.getId();
    this.userService.getById(id).subscribe(data=>{
      this.currentUser = data as User;
      const basicInfo ={
        firstName: this.currentUser.firstName,
        lastName: this.currentUser.lastName ,
        email: this.currentUser.email,
        birthDate: this.currentUser.birthDate,
        picture: this.currentUser.picture,
        language: this.currentUser.language,
      };
      const technicalInfo ={
        location: this.currentUser.location,
        seniority: this.currentUser.seniority,
        company: this.currentUser.company,
        skills: [...this.currentUser.skills],
        projectExperiences: [...this.currentUser.projectExperiences]
      };
      this.profileForm.get('basicInfo').setValue(basicInfo);
      this.profileForm.get('technicalInfo').setValue(technicalInfo);
    })
  }

  public onSubmit() {
    const basicInfoFormValue = this.profileForm.get('basicInfo').value;
    const technicalInfoFormValue = this.profileForm.get('technicalInfo').value;

    const company = technicalInfoFormValue.company.company;
    const user: User = {
      id: 0,
      username: this.authService.getUsername(),
      password: null,
      email: basicInfoFormValue.email,
      firstName: basicInfoFormValue.firstName,
      lastName: basicInfoFormValue.lastName,
      birthDate: basicInfoFormValue.birthDate,
      picture: null,
      //basicInfoFormValue.picture.blob,
      language: basicInfoFormValue.language,
      role: null,
      seniority: this.profileForm.get('technicalInfo').value.seniority.seniority,
      location: technicalInfoFormValue.location.location,
      company: {
        id: company.id ? company.id : 0,
        name: company.name ? company.name : company
      },
      skills: null,
      projectExperiences: null,
    };
    const skills = this.profileForm.get('technicalInfo').value.skills;
    const skillArray: UserSkill[] = [];

    //set user skills starts
    skills.forEach(skill => {
      const area = skill.technology.area;
      let finalArea = null;
      if(typeof area.area =='string'){
        finalArea = {
          id: 0,
          name: area.area
        }
      }
      else
        if(area.area!=undefined){
          finalArea = {
            id: area.area.id,
            name: area.area.name
          }
        }
        else{
          finalArea = {
            id: area.id,
            name: area.name
          }
        }
      const technology: Technology = {
        id: 0,
        name: skill.technology.name,
        area: finalArea
      };
      const skillLevel = skill.level;
      const userSkill: UserSkill = {
        id: 0,
        technology: technology,
        level: skillLevel.level ? skillLevel.level : skillLevel
      };
      skillArray.push(userSkill);
    });
    user.skills = skillArray;
    //set user skills ends

    //set project experiences starts
    const projectExperiences = this.profileForm.get('technicalInfo').value.projectExperiences;
    const projectExperiencesArray: ProjectExperience[] = [];

    projectExperiences.forEach(projectExp => {
      const userExperienceArray: UserExperience[] = [];
      const userExperiences = projectExp.project.userExperiences;
      userExperiences.forEach(userExp => {
        userExperienceArray.push(userExp);
      });
      const industry = projectExp.project.industry;
      let finalIndustry = null;
      if(typeof industry.industry == 'string'){
        finalIndustry={
          id: 0,
          name: industry.industry
        }
      }
      else
        if(industry.industry!=undefined){
          finalIndustry = {
            id: industry.industry.id,
            name: industry.industry.name
          }
        }
        else{
          finalIndustry = {
            id: industry.id,
            name: industry.name
          }
        }
      const company = projectExp.project.company;
      let finalCompany = null;
      if(typeof company.company == 'string'){
        finalCompany ={
          id: 0,
          name: company.company
        }
      }
      else
        if(company.company!=undefined){
          finalCompany = {
            id: company.company.id,
            name: company.company.name
          }
        }
        else{
          finalCompany = {
            id: company.id,
            name: company.name
          }
        }
      const project: Project = {
        id: 0,
        name: projectExp.project.name,
        description: projectExp.project.description,
        industry: finalIndustry,
        company: finalCompany,
        userExperiences: userExperienceArray
      };
      const projectExperience: ProjectExperience = {
        project: project,
        startDate: projectExp.startDate,
        endDate: projectExp.endDate,
        description: projectExp.description
      };
      projectExperiencesArray.push(projectExperience);
    });
    user.projectExperiences = projectExperiencesArray;
    //set project experiences ends

  }
}
