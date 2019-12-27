import { UserService } from 'src/app/core/services/user/user.service';
import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'teamup-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {

  form: FormGroup;
  id: number;
  keys: any;
  originalData: any;

  constructor(private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<UserDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private service: UserService) {

  }

  ngOnInit() {
    this.dialogRef.updateSize('30%', '61%');

    let formData = {};
    this.id = this.data.id;

    this.getEntryKeys();
    formData = this.getFormBuilderValues();

    this.form = this.formBuilder.group(
      formData
    );
  }

  getEntryKeys() {
    this.keys = Object.keys(this.data);
  }

  getFormBuilderValues() {
    // get data
    const formData = {};
    this.keys.forEach((key: string) => {
      if (key !== 'id') {
        return formData[key] = [this.data[key], Validators.required];
      }
    });
    return formData;
  }

  close() {
    this.dialogRef.close();

  }
}
