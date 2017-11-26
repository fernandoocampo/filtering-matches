import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // <-- NgModel lives here
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { MatchesComponent } from './matches/matches.component';
import { Configuration } from '../app.constants';
import { UserFinderService } from './user-finder.service';


@NgModule({
  declarations: [
    AppComponent,
    MatchesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    UserFinderService,
    Configuration
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
