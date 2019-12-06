import {Component, Inject, LOCALE_ID} from '@angular/core';

@Component({
  selector: 'teamup-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'teamup';
  languageList = [{
    code: 'en',
    label: 'English'
  }, {
    code: 'de',
    label: 'Deutsch'
  }, {
    code: 'ro',
    label: 'Romana'
  }];

  constructor(@Inject(LOCALE_ID) protected localeId: string) {}
}
