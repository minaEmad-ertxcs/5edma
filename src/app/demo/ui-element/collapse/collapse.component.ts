
import { Component } from '@angular/core';


import { SharedModule } from 'src/app/theme/shared/shared.module';

@Component({
  selector: 'app-collapse',
  imports: [SharedModule],
  templateUrl: './collapse.component.html',
  styleUrls: ['./collapse.component.scss']
})
export class CollapseComponent {
  
  isCollapsed = true;
  isMultiCollapsed1 = true;
  isMultiCollapsed2 = true;

  items = ['First', 'Second', 'Third'];
}
