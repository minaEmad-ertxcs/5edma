import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgApexchartsModule } from 'ng-apexcharts';
import { UserService } from 'src/app/services/user-service';
import { SharedModule } from 'src/app/theme/shared/shared.module';

@Component({
  selector: 'app-user-details',
  imports: [SharedModule, NgApexchartsModule],
  templateUrl: './user-details.html',
  styleUrl: './user-details.scss'
})
export class UserDetails implements OnInit {
  previewImage: string = 'assets/images/user/avatar-4.jpg';
  userId: string | null = null;
  selectedFile: File | null = null;
  user: any;
  form!: FormGroup;
  submitted = false;
  isLoading = false;

  constructor(private router: Router, private userService: UserService, private route: ActivatedRoute, private fb: FormBuilder) { }

  ngOnInit() {
    this.userId = this.route.snapshot.paramMap.get('id');

    console.log('Loaded user details for ID:', this.userId);

    this.form = this.fb.group({
      fullName: ['', [Validators.required, Validators.minLength(3)]],
      mobile: ['', [Validators.required, Validators.minLength(11)]],
      grade: ['', [Validators.required]],
      birthDate: ['', [Validators.required]],
      profileImage: [null]
    });

    // get the data from database

    this.user = {
      fullName: "mina emad",
      mobile: "01125037505",
      grade: "Grade 4",
      birthDate: "2018-04-15",
      profileImage: null
    }
  }

  get f() {
    return this.form.controls;
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    if (!this.selectedFile) return;

    const reader = new FileReader();
    reader.onload = () => {
      this.previewImage = reader.result as string;
    };
    reader.readAsDataURL(this.selectedFile);

    this.form.patchValue({
      profileImage: this.selectedFile
    });

    this.form.get('profileImage')?.updateValueAndValidity();
  }

  saveChanges() {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    const formData = new FormData();

    formData.append('fullName', this.form.value.fullName);
    formData.append('mobile', this.form.value.mobile);
    formData.append('grade', this.form.value.grade);
    formData.append('birthDate', this.form.value.birthDate);

    if (this.selectedFile) {
      formData.append('profileImage', this.selectedFile);
    }

    // this.http.post('https://your-api-url.com/api/update-user', formData)
    //   .subscribe({
    //     next: res => console.log("Saved successfully", res),
    //     error: err => console.error("Error", err)
    //   });
  }
}
