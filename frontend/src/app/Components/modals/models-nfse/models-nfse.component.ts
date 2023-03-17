import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticateService } from 'src/app/Services/authenticate.service';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Nfse } from 'src/app/Interfaces/nfse';
import { NfseService } from 'src/app/Services/nfse.service';
import { UserService } from 'src/app/Services/user.service';



@Component({
  selector: 'app-models-nfse',
  templateUrl: './models-nfse.component.html',
  styleUrls: ['./models-nfse.component.css']
})

export class ModelsNfseComponent implements OnInit {


  @Input() nfse!: Nfse;
  @Output() copiarNfse = new EventEmitter<Nfse>();


  nfses: Nfse[] = [];
  customersCount: any = {};
  totalBilling: number = 0;
  savedForm!: FormGroup

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
    private nfseService: NfseService,
    private userService: UserService
  ) {}

  ngOnInit() {
    if (!this.authenticateService.isUserLogged()) this.router.navigate(['login']);
    this.userService.getUser().subscribe((user) => {
      this.nfseService.getAllNfses(user.id).subscribe((Response) => {
        this.nfses = Response;
        this.nfses.forEach((nfse) => {
          this.customersCount[nfse.borrowerCnpjOrCpf] = true;
          this.totalBilling += nfse.amount;
        })
        this.customersCount = Object.keys(this.customersCount).length;
      })
    });
  }

  // função para copiar a nota fiscal do back-end
  copyNfse(id: number): void {
    this.nfseService.getNotaFiscalById(this.nfse.id).subscribe(
      (nfseData: Nfse) => {
        // atualiza a propriedade nfse com os dados da nota fiscal do back-end
        this.nfse = nfseData;
        this.copiarNfse.emit(this.nfse); // emite o evento com a nova nota fiscal
      },
      (      error: any) => {
        console.log(error);
      }
    );
  }

}
