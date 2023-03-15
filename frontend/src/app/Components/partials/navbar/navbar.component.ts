import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/Interfaces/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent {
  @Input() username: string | undefined;

}
