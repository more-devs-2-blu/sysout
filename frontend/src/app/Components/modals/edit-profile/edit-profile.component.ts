import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/Interfaces/user';
import { AuthenticateService } from 'src/app/Services/authenticate.service';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  editUser?: User;
  currentPassword: any;
  newPassword: any;
  checkNewPassword: any;

  ngOnInit() {
    if (!this.authenticateService.isUserLogged()) this.router.navigate(['login'])

    this.userService.getUser().subscribe(user => {
      this.editUser = user;
    });
  }

  submit() {
    console.log(this.editUser);

    if (this.currentPassword == this.editUser?.password) {
      if (this.newPassword == this.checkNewPassword) {
        this.userService.editPassword(this.newPassword).subscribe(
          (Response: string) => {
            console.log(Response);
          }, (error: HttpErrorResponse) => {
            alert(error);
          }
        );
      } else {
        console.log("As novas senhas não coincidem!");
      }
    } else {
      console.log("Senha atual errada!");

    }

    //this.userService.editUser(editForm.value).subscribe(
    //  (Response: User) => {
    //    console.log(Response);
    //
    //  },(error: HttpErrorResponse) =>{
    //    alert(error);
    //  }
    // );

  }

  constructor(
    private authenticateService: AuthenticateService,
    private router: Router,
    private userService: UserService
  ) { }
}
