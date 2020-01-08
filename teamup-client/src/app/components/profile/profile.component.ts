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
      //this.profileForm.get('technicalInfo').setValue(technicalInfo)
      console.log(this.profileForm.get('technicalInfo').setValue(technicalInfo));
    })
  }

  public onSubmit() {
    const basicInfoFormValue = this.profileForm.get('basicInfo').value;
    const technicalInfoFormValue = this.profileForm.get('technicalInfo').value;

    const user: User = {
      id: 0,
      username: null,
      password: null,
      email: basicInfoFormValue.email,
      firstName: basicInfoFormValue.firstName,
      lastName: basicInfoFormValue.lastName,
      birthDate: basicInfoFormValue.birthDate,
      picture: basicInfoFormValue.picture.blob,
      language: basicInfoFormValue.language,
      role: null,
      seniority: this.profileForm.get('technicalInfo').value.seniority.seniority,
      location: technicalInfoFormValue.location.location,
      company: {
        id: technicalInfoFormValue.company.company.id ? technicalInfoFormValue.company.company.id : 0,
        name: technicalInfoFormValue.company.company.name ? technicalInfoFormValue.company.company.name :
          technicalInfoFormValue.company.company
      },
      skills: null,
      projectExperiences: null,
    };

    const skills = this.profileForm.get('technicalInfo').value.skills;
    const skillArray: UserSkill[] = [];

    skills.forEach(skill => {
      const technology: Technology = {
        id: 0,
        name: skill.technology.techName,
        area: {
          id: skill.technology.techArea.techArea.id ? skill.technology.techArea.techArea.id : 0,
          name: skill.technology.techArea.techArea.name ? skill.technology.techArea.techArea.name : skill.technology.techArea.techArea
        }
      };

      const skillLevel = skill.level.level;

      const userSkill: UserSkill = {
        id: 0,
        technology: technology,
        level: skillLevel
      };
      skillArray.push(userSkill);
    });

    user.skills = skillArray;

    const projectExperiences = this.profileForm.get('technicalInfo').value.projectExperiences;
    const projectExperiencesArray: ProjectExperience[] = [];

    projectExperiences.forEach(projectExp => {
      const userExperienceArray: UserExperience[] = [];
      const userExperiences = projectExp.project.userExperience;

      userExperiences.forEach(userExp => {
        userExperienceArray.push(userExp);
      });

      const project: Project = {
        id: 0,
        name: projectExp.project.name,
        description: projectExp.project.description,
        industry: {
          id: projectExp.project.industry.industry.id ? projectExp.project.industry.industry.id : 0,
          name: projectExp.project.industry.industry.name ? projectExp.project.industry.industry.name : projectExp.project.industry.industry,
        },
        company: {
          id: projectExp.project.company.company.id ? projectExp.project.company.company.id : 0,
          name: projectExp.project.company.company.name ? projectExp.project.company.company.name : projectExp.project.company.company
        },
        userExperience: userExperienceArray
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
  }
}
