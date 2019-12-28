import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/services/user/user.service';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';

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
  }

}
