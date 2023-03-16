import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Issue } from '../Interfaces/issue';

@Injectable({
  providedIn: 'root'
})

export class IssueService {
  private apiUrl = environment.baseApiUrl;

  constructor(private http: HttpClient) { }

  public postIssue(issue: Issue): Observable<Issue>{
    return this.http.post<Issue>(`${this.apiUrl}/nfse/add`, issue)
  }
}
