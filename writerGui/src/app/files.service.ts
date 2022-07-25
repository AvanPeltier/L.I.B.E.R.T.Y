import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Files} from './files/files.component'
import { MessageService } from './message.service';
import { catchError, Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class FilesService {
  private filesUrl= "http://localhost:8080/files"
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  
  constructor(private http: HttpClient, private messageService: MessageService) {}

  getFiles(): Observable<Files[]>{
    return this.http.get<Files[]>(this.filesUrl);
  }

  getFile(id: number): Observable<Files>{
    return this.http.get<Files>(`${this.filesUrl}/${id}`)
  }

  getUserFile(user: string): Observable<Files[]>{
    return this.http.get<Files[]>(`${this.filesUrl}/?name=${user}`).pipe()
  }

  addFile(file: Files): Observable<Files> {
    console.log(this.http.post<Files>(this.filesUrl, file, this.httpOptions).pipe())
    return this.http.post<Files>(this.filesUrl, file, this.httpOptions).pipe()
  }

}
