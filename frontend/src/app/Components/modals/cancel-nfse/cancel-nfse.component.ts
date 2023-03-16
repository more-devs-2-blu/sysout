import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticateService } from 'src/app/Services/authenticate.service';
import { FormGroup, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'app-cancel-nfse',
  templateUrl: './cancel-nfse.component.html',
  styleUrls: ['./cancel-nfse.component.css']
})
export class CancelNfseComponent {

  cancelForm!: FormGroup

  // função para validar campo descrição
  get isDescriptionEmpty() {
    const control = this.cancelForm.get('description');
    return control?.touched && control.invalid;
  }

  ngOnInit() {
    this.cancelForm = new FormGroup({
      description: new FormControl('', [Validators.required]),
    })
    if (!this.authenticateService.isUserLogged()) this.router.navigate(['login'])
  }

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router
  ) {}

}
