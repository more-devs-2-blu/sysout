import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Nfse } from 'src/app/Interfaces/nfse';

@Component({
  selector: 'app-actions-dropdown',
  templateUrl: './actions-dropdown.component.html',
  styleUrls: ['./actions-dropdown.component.css']
})

export class ActionsDropdownComponent {

  @Input() nfse!: Nfse;

}
