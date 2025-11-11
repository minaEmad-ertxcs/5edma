
import { Component } from '@angular/core';


import { SharedModule } from 'src/app/theme/shared/shared.module';

@Component({
  selector: 'app-nav-search',
  imports: [SharedModule],
  templateUrl: './nav-search.component.html',
  styleUrls: ['./nav-search.component.scss']
})
export class NavSearchComponent {
  
  searchOn: boolean;

  
  constructor() {
    this.searchOn = false;
  }
}
