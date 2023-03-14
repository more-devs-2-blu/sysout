import { Component, OnDestroy, OnInit } from '@angular/core';
import { Authenticate } from 'src/app/Interfaces/authenticate';
import { AuthenticateService } from 'src/app/Services/authenticate.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],

})
export class LoginPageComponent implements OnInit, OnDestroy {

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    document.body.classList.add('bg-purple');
    localStorage.removeItem('token');
  }

  ngOnDestroy(): void {
    document.body.classList.remove('bg-purple');
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
        setTimeout(() => { this.router.navigate(['']) }, 200)
    }, (error) => {
      document.querySelector('.error')?.classList.add('active')
    });

  }

}
