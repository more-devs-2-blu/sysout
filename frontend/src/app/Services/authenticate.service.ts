import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Authenticate } from '../Interfaces/authenticate';
import { Token } from '../Interfaces/token';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {
  private apiUrl = environment.baseApiUrl;

  constructor(private http: HttpClient) { }

  public login(user: Authenticate): Observable<Token> {
    return this.http.post<Token>(`${this.apiUrl}/auth/login`, user)
  }

  isUserLogged(): boolean {
    return Boolean(localStorage.getItem('token'));
  }
}
