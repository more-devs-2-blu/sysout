import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../Interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = environment.baseApiUrl;

  constructor(private http: HttpClient) { }

  headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`)

  public getUser(): Observable<User>{
    return this.http.get<User>(`${this.apiUrl}/user/`, { headers: this.headers })
  }

  public editPassword(newPassword: string): Observable<string>{
    return this.http.put<string>(`${this.apiUrl}/user/edit`, newPassword)
  }
}
