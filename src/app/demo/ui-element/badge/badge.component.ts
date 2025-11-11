
import { Component } from '@angular/core';


import { SharedModule } from 'src/app/theme/shared/shared.module';

@Component({
  selector: 'app-badge',
  imports: [SharedModule],
  templateUrl: './badge.component.html',
  styleUrls: ['./badge.component.scss']
})
export class BadgeComponent {}
