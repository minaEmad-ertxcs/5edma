
import { Component } from '@angular/core';


import { SharedModule } from 'src/app/theme/shared/shared.module';


import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-form-elements',
  imports: [SharedModule, NgbDropdownModule],
  templateUrl: './form-elements.component.html',
  styleUrls: ['./form-elements.component.scss']
})
export class FormElementsComponent {}
