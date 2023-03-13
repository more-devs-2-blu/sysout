import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticateService } from 'src/app/Services/authenticate.service';

@Component({
  selector: 'app-emissao',
  templateUrl: './emissao.component.html',
  styleUrls: ['./emissao.component.css']
})
export class EmissaoComponent {

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
  ) {}

  ngOnInit() {
    if (!this.authenticateService.isUserLogged()) this.router.navigate([''])
  }
}
