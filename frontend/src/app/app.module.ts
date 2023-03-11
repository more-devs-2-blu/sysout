import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './Components/login-page/login-page.component';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './Components/home/home.component';

const routes: Routes = [
  { path: '', component: LoginPageComponent },
  { path: 'home', component: HomeComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
