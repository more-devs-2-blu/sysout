import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './Components/login-page/login-page.component';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './Components/home/home.component';
import { IssueComponent } from './Components/issue/issue.component';
import { ModelsNfseComponent } from './Components/modals/models-nfse/models-nfse.component';
import { CancelNfseComponent } from './Components/modals/cancel-nfse/cancel-nfse.component';
import { NavbarComponent } from './Components/partials/navbar/navbar.component';
import { FooterComponent } from './Components/partials/footer/footer.component';
import { ErrorPageComponent } from './Components/error-page/error-page.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'issue', component: IssueComponent},
  { path: 'models', component: ModelsNfseComponent},
  { path: 'cancelar', component: CancelNfseComponent},
  { path: '**', component: ErrorPageComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HomeComponent,
    IssueComponent,
    ModelsNfseComponent,
    ErrorPageComponent,
    NavbarComponent,
    FooterComponent,
    CancelNfseComponent
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
