
import { Component, output } from '@angular/core';


import { SharedModule } from 'src/app/theme/shared/shared.module';
import { NavContentComponent } from './nav-content/nav-content.component';

@Component({
  selector: 'app-navigation',
  imports: [SharedModule, NavContentComponent],
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent {
  
  windowWidth: number;
  NavMobCollapse = output();

  
  constructor() {
    this.windowWidth = window.innerWidth;
  }

  
  navMobCollapse() {
    if (this.windowWidth < 992) {
      this.NavMobCollapse.emit();
    }
  }
}
