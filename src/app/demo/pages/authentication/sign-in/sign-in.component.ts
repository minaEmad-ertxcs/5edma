import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'src/app/theme/shared/shared.module';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [SharedModule, RouterModule, FormsModule],
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {
  username: string = '';
  password: string = '';

  private apiUrl = 'https://temp-backend-url.com/api/login';

  constructor(private http: HttpClient, private router: Router) { }

  onLogin() {
    const body = {
      username: this.username,
      password: this.password
    };

    this.http.post(this.apiUrl, body).subscribe({
      next: (response) => {
        console.log('Login successful:', response);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.error('Login failed:', error);
        alert('Invalid credentials');
      }
    });
  }
}