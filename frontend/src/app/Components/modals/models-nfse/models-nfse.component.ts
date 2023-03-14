import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticateService } from 'src/app/Services/authenticate.service';

@Component({
  selector: 'app-models-nfse',
  templateUrl: './models-nfse.component.html',
  styleUrls: ['./models-nfse.component.css']
})
export class ModelsNfseComponent {

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
  ) {}

  ngOnInit() {
    if (!this.authenticateService.isUserLogged()) this.router.navigate(['login'])
  }

}
