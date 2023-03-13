import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './Components/login-page/login-page.component';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './Components/home/home.component';
import { IssueComponent } from './Components/issue/issue.component'

const routes: Routes = [
  { path: '', component: LoginPageComponent },
  { path: 'home', component: HomeComponent },
  { path: 'emissao', component: IssueComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HomeComponent,
    IssueComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    ReactiveFormsModule
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
