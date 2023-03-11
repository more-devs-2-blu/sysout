import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticateService } from 'src/app/Services/authenticate.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router
  ) {}

  ngOnInit() {
    document.body.style.backgroundColor = '';
    if (!this.authenticateService.isUserLogged()) this.router.navigate([''])
  }
}
