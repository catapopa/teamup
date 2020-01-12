import { Component, OnInit } from '@angular/core';
import {User} from "../../shared/models/user";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../core/services/user/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'teamup-approve-profile',
  templateUrl: './approve-profile.component.html',
  styleUrls: ['./approve-profile.component.scss']
})
export class ApproveProfileComponent implements OnInit {

  currentUser: User;
  username: string;
  profileForm: FormGroup;

  constructor(private userService: UserService, formBuilder: FormBuilder, private route: ActivatedRoute) {
    this.profileForm = formBuilder.group({
      basicInfo: new FormControl(null, [Validators.required]),
      technicalInfo: new FormControl(null, [Validators.required]),
    });

    this.route.params.subscribe(params => {
      this.username = params['username'];
    });
  }

  ngOnInit() {
    const id = 2;
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
    });
  }

  approveProfile() {
    console.log("profile approved")
  }
}
