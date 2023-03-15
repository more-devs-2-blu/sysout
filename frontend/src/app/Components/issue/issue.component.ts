import { DatePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Issue } from 'src/app/Interfaces/issue';
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
  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
    private userService: UserService,
    private issueService: IssueService 
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
  
  public onIssueNFS(addForm: NgForm){
    this.issueService.postIssue(addForm.value).subscribe(
      (Response: Issue) =>{
        console.log(Response);   
      },(error: HttpErrorResponse) =>{
        alert(error);
      }
      );
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
      return 5.00;
    }
    if(this.servType == 1406){
      return 3.00;
    }else{
      return 2.00;
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
