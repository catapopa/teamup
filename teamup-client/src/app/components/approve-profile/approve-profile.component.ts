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

  username: string;
  profileForm: FormGroup;
  //wrapped containing user object and its profile picture
  currentLoggedInUserWrapped: any;
  //logged in user object
  currentLoggedInUser: any;
  //logged in user's profile picture
  profilePicture: string;


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
  approveProfile() {
    console.log("profile approved")
  }
}
