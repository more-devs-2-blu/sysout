import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './Components/login-page/login-page.component';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './Components/home/home.component';
import { EmissaoComponent } from './Components/emissao/emissao.component';
import { ModelosNotasComponent } from './modelos-notas/modelos-notas.component';
import { CancelNfseComponent } from './Components/cancel-nfse/cancel-nfse.component';

const routes: Routes = [
  { path: '', component: LoginPageComponent },
  { path: 'home', component: HomeComponent },
  { path: 'emissao', component: EmissaoComponent},
  { path: 'modelos', component: ModelosNotasComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HomeComponent,
    EmissaoComponent,
    ModelosNotasComponent,
    CancelNfseComponent
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
