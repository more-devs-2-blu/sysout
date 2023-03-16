import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Nfse } from '../Interfaces/nfse';

@Injectable({
  providedIn: 'root'
})

export class NfseService {
  private apiUrl = environment.baseApiUrl;

  constructor(private http: HttpClient) { }

  public getAllNfses(userId: string): Observable<Nfse[]> {
    return this.http.get<Nfse[]>(`${this.apiUrl}/nfse/all/${userId}`);
  }
}
