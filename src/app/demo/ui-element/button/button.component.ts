
import { Component } from '@angular/core';


import { SharedModule } from 'src/app/theme/shared/shared.module';

@Component({
  selector: 'app-button',
  imports: [SharedModule],
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent {}
