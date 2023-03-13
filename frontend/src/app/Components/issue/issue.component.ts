import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticateService } from 'src/app/Services/authenticate.service';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {
  step: any = 1;
  todayDate = new Date().toLocaleDateString();
  servType: any;
  localCod: any;
  totalValue: number = 0.00;
  discount: number = 0.00;

  //formatação do json do form
  noteData = new FormGroup({
    prestador: new FormGroup({
      cnpj: new FormControl(''),
      razaoSocial: new FormControl(''),
      cadastroEco: new FormControl(''),
      serie: new FormControl(''),
      tipo: new FormControl(''),
      dataEmissao: new FormControl(''),
      dataFato: new FormControl(''),
    }),
    servico: new FormGroup({
      enquadramento: new FormControl(''),
      localPrest: new FormControl(''),
      codLocal: new FormControl(''),
      localInc: new FormControl(''),
      discrim: new FormControl(''),
    }),
    tomador: new FormGroup({
      tipoTomador: new FormControl(''),
      tomador: new FormControl(''),
      cnpjToma: new FormControl(''),
      cadastroEcoToma: new FormControl(''),
      cep: new FormControl(''),
      cidade: new FormControl(''),
      pais: new FormControl(''),
      bairro: new FormControl(''),
      logra: new FormControl(''),
    }),
    dadosFiscais: new FormGroup({
      sitTribu: new FormControl(''),
      aliquota: new FormControl(''),
      valor: new FormControl(''),
      descInco: new FormControl(''),
      valorDed: new FormControl(''),
      baseCalc: new FormControl(''),
      issqn: new FormControl(''),
      issrf: new FormControl(''),
    }),
    tribFed: new FormGroup({
      irFed: new FormControl(''),
      pis: new FormControl(''),
      cofins: new FormControl(''),
    }),
    valores: new FormGroup({
      valorTotal: new FormControl(''),
      descInc: new FormControl(''),
      deducao: new FormControl(''),
      baseCalc: new FormControl(''),
      totalIssqn: new FormControl(''),
      totalIssrf: new FormControl(''),
      totalFed: new FormControl(''),
      descCond: new FormControl(''),
      valorLiq: new FormControl(''),
    })
  })

  //função para enviar o json para o backend
  submit(){
    console.log(this.noteData.value);
  }

  //função para troca de etapa do form
  next() {
    this.step = this.step + 1;
  }
  previous() {
    this.step = this.step - 1;
  }

  //Autocomplete baseado no local de prestação de serviço
  localCode(){
    if(this.localCod == null){
      return '';
    }
    if(this.localCod == 'Blumenau'){
      return 8047;
    }else{
      return 8357;
    }
  }

  //Autocomplete do campo situação tributaria
  tribSituation(){
    if(this.servType != 702){
      return '00';
    } else {
      return '';
    }
  }

  aliqValue(){
    if(this.servType == 702){
      return '5,00';
    }
    if(this.servType == 1406){
      return '3,00';
    }else{
      return '2,00'
    }    
  }

  liqValue(){
    return this.totalValue - this.discount;
  }

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router
  ) { }

  ngOnInit() {
    document.body.style.backgroundColor = '';
    if (!this.authenticateService.isUserLogged()) this.router.navigate([''])
  }
}
