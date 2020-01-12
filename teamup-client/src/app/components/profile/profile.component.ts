import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/services/user/user.service';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { User } from '../../shared/models/user';
import { UserSkill } from '../../shared/models/userSkill';
import { ProjectExperience } from '../../shared/models/projectExperience';
import { Technology } from '../../shared/models/technology';
import { Project } from '../../shared/models/project';
import { UserExperience } from '../../shared/models/userExperience';
import {UserWithPictureWrapper} from "../../shared/models/userWithPictureWrapper";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'teamup-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  //wrapped containing user object and its profile picture
  currentLoggedInUserWrapped: any;
  //logged in user object
  currentLoggedInUser: any;
  //logged in user's profile picture
  profilePicture: string;
  profileForm: FormGroup;

  constructor(private userService: UserService, formBuilder: FormBuilder, private snackbar: MatSnackBar) {
    this.profileForm = formBuilder.group({
      basicInfo: new FormControl(null, [Validators.required]),
      technicalInfo: new FormControl(null, [Validators.required]),
    });
  }

  ngOnInit() {
    if (localStorage.getItem('currentUser')) {
      let currentLoggedInUserName = JSON.parse(localStorage.getItem('currentUser'));
      this.userService.getUserWithPicture('userWithPicture/', currentLoggedInUserName).subscribe(obj => {
        this.currentLoggedInUserWrapped = obj;
        //retrieve data from user with picture wrapper obj
        this.currentLoggedInUser = this.currentLoggedInUserWrapped.userToUpdate;
        this.profilePicture = this.currentLoggedInUserWrapped.profilePicture;
        const basicInfo ={
          firstName: this.currentLoggedInUser.firstName,
          lastName: this.currentLoggedInUser.lastName ,
          email: this.currentLoggedInUser.email,
          birthDate: this.currentLoggedInUser.birthDate,
          picture: this.profilePicture,
          language: this.currentLoggedInUser.language,
        };
        const technicalInfo ={
          location: this.currentLoggedInUser.location,
          seniority: this.currentLoggedInUser.seniority,
          company: this.currentLoggedInUser.company,
          skills: [...this.currentLoggedInUser.skills],
          projectExperiences: [...this.currentLoggedInUser.projectExperiences]
        };
        this.profileForm.get('basicInfo').setValue(basicInfo);
        this.profileForm.get('technicalInfo').setValue(technicalInfo);
      });


    }
  }

  public onSubmit() {
    const basicInfoFormValue = this.profileForm.get('basicInfo').value;
    const technicalInfoFormValue = this.profileForm.get('technicalInfo').value;
    const company = technicalInfoFormValue.company.company;
    const user: User = {
      id: this.currentLoggedInUser.id,
      username: this.currentLoggedInUser.username,
      password: null,
      email: basicInfoFormValue.email,
      firstName: basicInfoFormValue.firstName,
      lastName: basicInfoFormValue.lastName,
      birthDate: basicInfoFormValue.birthDate,
      picture: basicInfoFormValue.picture.blob,
      language: basicInfoFormValue.language,
      role: this.currentLoggedInUser.role,
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
        id: skill.technology.id ? skill.technology.id : 0,
        name: skill.technology.name,
        area: finalArea
      };
      const skillLevel = skill.level;
      const userSkill: UserSkill = {
        id: skill.id? skill.id : 0,
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
      userExperiences.forEach(userExp=> {
        userExp.userId = this.currentLoggedInUser.id;
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


    //workaround for user with profile picture
    user.id = this.currentLoggedInUser.id;
    const profilePicture = user.picture;
    user.picture = null;
    const userToBeUpdated: UserWithPictureWrapper = {
      userToUpdate: user,
      profilePicture: profilePicture,
    };
    console.log(userToBeUpdated.userToUpdate);

    this.userService.update('update', userToBeUpdated).subscribe(
        () => {
          this.snackbar.open('User saved.');
        },
        () => {
          this.snackbar.open('Error occured.');
        }
    );

  }
}
