import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticateService } from 'src/app/Services/authenticate.service';

@Component({
  selector: 'app-modelos-notas',
  templateUrl: './modelos-notas.component.html',
  styleUrls: ['./modelos-notas.component.css']
})
export class ModelosNotasComponent {

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
  ) {}

  ngOnInit() {
    if (!this.authenticateService.isUserLogged()) this.router.navigate(['login'])
  }

}
