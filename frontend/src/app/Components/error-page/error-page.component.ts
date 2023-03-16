import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/Services/user.service';
import { User } from 'src/app/Interfaces/user';
import { AuthenticateService } from 'src/app/Services/authenticate.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.css']
})
export class ErrorPageComponent implements OnInit {
  user!: User;
  username!: string;

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
    private userService: UserService,
  ) {}

  ngOnInit() {
    if (!this.authenticateService.isUserLogged()) this.router.navigate(['login']);
    this.userService.getUser().subscribe((user) => {
      this.user = user;
      localStorage.setItem('userId', user.id);
      this.username = user.name.split(' ')[0];
    });
  }
}
