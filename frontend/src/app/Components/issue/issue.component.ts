import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/Interfaces/user';
import { AuthenticateService } from 'src/app/Services/authenticate.service';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {
  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
    private userService: UserService
  ) { }
  
  step: any = 1;
  todayDate = new Date().toLocaleDateString();
  servType: any;
  localCod: any;
  totalValue: number = 0.00;
  discount: number = 0.00;
  public showErrorMessage = false;
  user: User | null = null;
  
  ngOnInit() {
    document.body.style.backgroundColor = '';
    if (!this.authenticateService.isUserLogged()) this.router.navigate(['']);
    
    this.userService.getUser().subscribe(user => {
      this.user = user;
    });   
  
  }
  //formatação do json do form
  noteData = new FormGroup({
    prestador: new FormGroup({
      cnpj: new FormControl(''),
      razaoSocial: new FormControl(''),
      cadastroEco: new FormControl(''),
      serie: new FormControl(''),
      tipo: new FormControl(''),
      dataEmissao: new FormControl(this.todayDate),
      dataFato: new FormControl(this.todayDate),
    }),
    servico: new FormGroup({
      enquadramento: new FormControl('', Validators.required),
      localPrest: new FormControl('', Validators.required),
      codLocal: new FormControl(''),
      localInc: new FormControl('Timbó'),
      discrim: new FormControl('', Validators.required),
    }),
    tomador: new FormGroup({
      tipoTomador: new FormControl('', Validators.required),
      tomador: new FormControl('', Validators.required),
      cnpjToma: new FormControl('', Validators.required),
      cadastroEcoToma: new FormControl(''),
      cep: new FormControl('', Validators.required),
      cidade: new FormControl('', Validators.required),
      pais: new FormControl(''),
      bairro: new FormControl('', Validators.required),
      logra: new FormControl('', Validators.required),
    }),
    dadosFiscais: new FormGroup({
      sitTribu: new FormControl(this.tribSituation()),
      aliquota: new FormControl('2,00'),
      valor: new FormControl('0,00', Validators.required),
      descInco: new FormControl('0,00'),
      valorDed: new FormControl('0,00'),
      baseCalc: new FormControl('0,00'),
      issqn: new FormControl('0,00'),
      issrf: new FormControl('0,00'),
    }),
    tribFed: new FormGroup({
      irFed: new FormControl('0,00'),
      pis: new FormControl('0,00'),
      cofins: new FormControl('0,00'),
    }),
    valores: new FormGroup({
      valorTotal: new FormControl('0,00'),
      descInc: new FormControl('0,00'),
      deducao: new FormControl('0,00'),
      baseCalc: new FormControl('1,00'),
      totalIssqn: new FormControl('0,00'),
      totalIssrf: new FormControl('0,00'),
      totalFed: new FormControl('0,00'),
      descCond: new FormControl('0,00'),
      valorLiq: new FormControl('0,00'),
    })
  })
  
  //função para enviar o json para o backend
  submit(){
    if(this.noteData.valid){
      console.log(this.noteData);
    }else{
      this.showErrorMessage = true;
      console.log("Preencha todos os campos");
    }
  }
  //função para troca de etapa do form
  next() {
    this.step = this.step + 1;
  }
  previous() {
    if(this.step == 3){
      this.step = this.step - 1;
      this.showErrorMessage = false;
    } else{
      this.step = this.step - 1;
    }
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
      return "";
    }
  }
  //Autocomplete aliquota
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
  //Autocomplete valor liquido
  liqValue(){
    return this.totalValue - this.discount;
  }

  issqnValue(){
    return 0.03 * this.totalValue;
  }
  
}
