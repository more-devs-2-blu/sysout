import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Nfse } from 'src/app/Interfaces/nfse';
import { User } from 'src/app/Interfaces/user';
import { AuthenticateService } from 'src/app/Services/authenticate.service';
import { NfseService } from 'src/app/Services/nfse.service';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user!: User;
  username!: string;
  nfses: Nfse[] = [];
  customersCount: any = {};
  totalBilling: number = 0;

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
    private userService: UserService,
    private nfseService: NfseService
  ) {}

  ngOnInit() {
    if (!this.authenticateService.isUserLogged()) this.router.navigate(['login']);
    this.userService.getUser().subscribe((user) => {
      this.user = user;
      this.username = user.name.split(' ')[0];
      this.nfseService.getAllNfses(user.id).subscribe((Response) => {
        this.nfses = Response;
        this.nfses.forEach((nfse) => {
          this.customersCount[nfse.cpfCnpjTomador] = true;
          this.totalBilling += nfse.valorServico;
        })
        this.customersCount = Object.keys(this.customersCount).length;
      })
    });
  }


}
