import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticateService } from 'src/app/Services/authenticate.service';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Nfse } from 'src/app/Interfaces/nfse';

@Component({
  selector: 'app-cancel-nfse',
  templateUrl: './cancel-nfse.component.html',
  styleUrls: ['./cancel-nfse.component.css']
})
export class CancelNfseComponent {

  @Input() nfse!: Nfse;

  cancelForm!: FormGroup

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router
    ) { }

    ngOnInit() {
      this.cancelForm = new FormGroup({
        description: new FormControl('', [Validators.required]),
      })

      if (!this.authenticateService.isUserLogged()) this.router.navigate(['login'])
    }

    // função para validar campo descrição
    get isDescriptionEmpty() {
      const control = this.cancelForm.get('description');
      return control?.touched && control.invalid;
    }
  }
