import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/services/user/user.service';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import {User} from "../../shared/models/user";
import {UserLanguage} from "../../shared/models/userLanguage";
import {UserRole} from "../../shared/models/userRole";
import {UserSeniority} from "../../shared/models/userSeniority";
import {Company} from "../../shared/models/company";
import {UserSkill} from "../../shared/models/userSkill";
import {ProjectExperience} from "../../shared/models/projectExperience";
import {Technology} from "../../shared/models/technology";
import {TechnologyArea} from "../../shared/models/technologyArea";
import {Project} from "../../shared/models/project";
import {UserExperience} from "../../shared/models/userExperience";

@Component({
  selector: 'teamup-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  profileForm: FormGroup;

  constructor(private userService: UserService, formBuilder: FormBuilder) {
    this.profileForm = formBuilder.group({
      basicInfo: new FormControl('', [Validators.required]),
      technicalInfo: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit() {
  }

  public onSubmit(){
    console.log("Val", this.profileForm.value);
    const user : User ={
      id: 0,
      username: null,
      password: null,
      email: null,
      firstName: this.profileForm.get('basicInfo').value.firstName,
      lastName: this.profileForm.get('basicInfo').value.lastName,
      birthDate: this.profileForm.get('basicInfo').value.birthDate,
      picture: null,
      language: this.profileForm.get('basicInfo').value.language,
      role: null,
      seniority: this.profileForm.get('technicalInfo').value.seniority.seniority,
      location: null,
      company: {
        id: 0,
        name: this.profileForm.get('technicalInfo').value.company.name
      },
      skills: null,
      projectExperiences: null,
    } ;
    const skills = this.profileForm.get('technicalInfo').value.skills;
    let skillArray: UserSkill[] =[];
    skills.forEach(skill=>{
      const technology: Technology ={
        id: 0,
        name: skill.technology.techName,
        area:{
          id: 0,
          name: skill.technology.techArea.name
        }
      };
      const skillLevel = skill.skillLevel.skillLevel;
      const userSkill : UserSkill ={
        id: 0,
        technology: technology,
        level: skillLevel
      };
      skillArray.push(userSkill);
    });
    user.skills = skillArray;
    const projectExperiences = this.profileForm.get('technicalInfo').value.projectExperiences;
    let projectExperiencesArray: ProjectExperience[]=[];
    projectExperiences.forEach(projectExp=>{
      let userExperienceArray: UserExperience[]=[];
      let userExperiences = projectExp.project.userExperience;
      userExperiences.forEach(userExp=>{
        userExperienceArray.push(userExp);
      });
      let project:Project={
        id: 0,
        name: projectExp.project.name,
        description: projectExp.project.description,
        industry:{
          id: 0,
          name: projectExp.project.industry.name,
        },
        company: {
          id:0,
          name: projectExp.project.company.name,
        },
        userExperience: userExperienceArray
      };
      let projectExperience:ProjectExperience={
        project: project,
        startDate: projectExp.startDate,
        endDate: projectExp.endDate,
        description: projectExp.description
      };
      projectExperiencesArray.push(projectExperience)
    });
    user.projectExperiences = projectExperiencesArray;
    console.log(user);
  }

}
