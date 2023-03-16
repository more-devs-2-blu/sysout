import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Address } from '../Interfaces/address';
import { Nfse } from '../Interfaces/nfse';

@Injectable({
  providedIn: 'root'
})

export class IssueService {
  private apiUrl = environment.baseApiUrl;

  constructor(private http: HttpClient) { }

  public postIssue(issue: Nfse): Observable<Nfse>{
    return this.http.post<Nfse>(`${this.apiUrl}/nfse/add`, issue);
  }

  public getCep(cep: string): Observable<Address> {
    return this.http.get<Address>(`https://viacep.com.br/ws/${cep}/json/`);
  }
}
