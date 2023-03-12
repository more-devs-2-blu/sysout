import { Component, OnInit } from '@angular/core';
import { Authenticate } from 'src/app/Interfaces/authenticate';
import { AuthenticateService } from 'src/app/Services/authenticate.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],

})
export class LoginPageComponent implements OnInit {

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router
  ) {}

  ngOnInit(): void {
    document.body.style.backgroundColor = 'var(--color-light-purple)';
    localStorage.removeItem('token');
  }

  onLogin(): void {
    const userDocIdentification = document.querySelector('input[name="userDocIdentification"]') as HTMLInputElement;
    const password = document.querySelector('input[name="password"]') as HTMLInputElement;
    const user: Authenticate = {
      userDocIdentification: userDocIdentification.value,
      password: password.value
    };

    this.authenticateService.login(user).subscribe(
      (response) => {
        localStorage.setItem('token', response.token);
        setTimeout(() => { this.router.navigate(['/home']) }, 200)
    }, (error) => {
      document.querySelector('.error')?.classList.add('active')
    });

  }

}
