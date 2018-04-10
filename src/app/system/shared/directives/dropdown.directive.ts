import { Directive, HostBinding, HostListener } from '@angular/core';

@Directive({
  selector: '[tcDropdown]'
})

export class DropdownDirective {
  @HostBinding('class.open') isOpen = false;

  @HostListener('click') onClick() {
    this.isOpen = !this.isOpen;
  }

}
