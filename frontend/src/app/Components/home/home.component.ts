import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Interfaces/user';
import { AuthenticateService } from 'src/app/Services/authenticate.service';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user!: User;
  username!: string;

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit() {
    if (!this.authenticateService.isUserLogged()) this.router.navigate(['login']);
    this.userService.getUser().subscribe((user) => {
      this.user = user;
      this.username = user.name.split(' ')[0];
    });
  }


}
