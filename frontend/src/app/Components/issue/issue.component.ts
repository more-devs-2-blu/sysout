import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Address } from 'src/app/Interfaces/address';
import { Nfse } from 'src/app/Interfaces/nfse';
import { User } from 'src/app/Interfaces/user';
import { AuthenticateService } from 'src/app/Services/authenticate.service';
import { IssueService } from 'src/app/Services/issue.service';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {

  step: number = 1;
  todayDate = new Date().getDate();
  servType: any;
  localCod: any;
  totalValue: number = 0.00;
  discount: number = 0.00;
  showErrorMessage = false;
  user!: User;
  username!: string;
  issue?: Nfse;
  address: Address = {
    'cep': '',
    'localidade': '',
    'bairro': '',
    'logradouro': '',
    'uf': '',
  };
  pais: string = 'Brasil';

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
    private userService: UserService,
    private issueService: IssueService
  ) { }


  ngOnInit() {
    if (!this.authenticateService.isUserLogged()) this.router.navigate(['']);

    this.userService.getUser().subscribe(user => {
      this.user = user;
      this.username = user.name.split(' ')[0];
    });

    this.currentStep(this.step);
  }

  onIssueEmit(form: NgForm): void {
    console.log(form.value);

    this.issueService.postIssue(form.value).subscribe((response: Nfse) => {
      console.log(response);
    }, (error: HttpErrorResponse) => {
      alert(error.message)
    })
  }

  searchCEP(): void {
    const cep = document.getElementById('issue-cep') as HTMLInputElement;
    if (cep && cep.value.length === 9) {
      this.issueService.getCep(cep.value).subscribe((address) => {
        this.address = address;
      })
    }

  }

  currentStep(step: number) {
    const steps = document.querySelectorAll('.step') as NodeListOf<HTMLDivElement>;
    steps.forEach((stepEl) => {
      stepEl.style.display = 'none';
    });

    const currentStepEl = document.querySelector(`#step${step}`) as HTMLDivElement;
    currentStepEl.style.display = '';
  }

  //função para troca de etapa do form
  next() {
    this.step = this.step + 1;
    this.currentStep(this.step);
  }

  previous() {
    if (this.step == 3) {
      this.step = this.step - 1;
      this.currentStep(this.step);
      this.showErrorMessage = false;
    } else {
      this.step = this.step - 1;
      this.currentStep(this.step);
    }
  }

  //Autocomplete baseado no local de prestação de serviço
  localCode() {
    if (this.localCod == null) {
      return '';
    }
    if (this.localCod == 'Blumenau') {
      return 8047;
    } else {
      return 8357;
    }
  }

  //Autocomplete do campo situação tributaria
  tribSituation() {
    if (this.servType != 702) {
      return '00';
    } else {
      return "";
    }
  }
  //Autocomplete aliquota
  aliqValue() {
    if (this.servType == 702) {
      return 5.00;
    }
    if (this.servType == 1406) {
      return 3.00;
    } else {
      return 2.00;
    }
  }
  //Autocomplete valor liquido
  liqValue() {
    return this.totalValue - this.discount;
  }

  issqnValue() {
    return 0.03 * this.totalValue;
  }

}
