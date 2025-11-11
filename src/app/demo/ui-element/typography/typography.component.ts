
import { Component } from '@angular/core';


import { SharedModule } from 'src/app/theme/shared/shared.module';

@Component({
  selector: 'app-typography',
  imports: [SharedModule],
  templateUrl: './typography.component.html',
  styleUrls: ['./typography.component.scss']
})
export class TypographyComponent {}
