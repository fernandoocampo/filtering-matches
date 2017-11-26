import { Injectable } from '@angular/core';
import { User } from './user';
import { UserFilter } from './user-filter';
import { Result } from './result';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Configuration } from '../app.constants';

@Injectable()
export class UserFinderService {

  // URL to the user finder api
  private usersUrl: string;

  /**
   * 
   * @param http http client to consume the API.
   * @param _configuration Application configuration object.
   */
  constructor(
    private http: HttpClient, 
    private _configuration: Configuration
  ) { 
    this.usersUrl = _configuration.ServerWithApiUrl;
  }

  /**
   * Invoke the remote service to search users.
   */
  getUsers(userFilter:UserFilter): Observable<Result[]> {
    var queryparams = this.buildUserFinderQueryParams(userFilter);
    //this.http.request.
    return this.http.get<Result[]>(this.usersUrl + queryparams)
      .pipe(
        tap(result => this.log('fetched users')),
        catchError(this.handleError('getUsers', []))
      );
  }

  /**
   * Build the query params required by user finder service.
   * @param userFilter The filters selected by the user.
   */
  buildUserFinderQueryParams(userFilter:UserFilter): string {
    var isfirstset = false;
    var query = '';
    
    if(userFilter != null) {
      query = '?';
      if(userFilter.hasphoto) {
        query = query + "hasphoto=" + userFilter.hasphoto + "&";
      }
      if(userFilter.incontact) {
        query = query + "incontact=" + userFilter.incontact + "&";
      }
      if(userFilter.isfavourite) {
        query = query + "isfavourite=" + userFilter.isfavourite + "&";
      }
      if(userFilter.minimumage != null || userFilter.minimumage != undefined) {
        query = query + "minage=" + userFilter.minimumage + "&";
      }
      if(userFilter.maximumage != null || userFilter.maximumage != undefined) {
        query = query + "maxage=" + userFilter.maximumage + "&";
      }
      if(userFilter.mincompatibilityscore) {
        query = query + "mincompatibilityscore=" + userFilter.mincompatibilityscore + "&";
      }
      if(userFilter.maxcompatibilityscore) {
        query = query + "maxcompatibilityscore=" + userFilter.maxcompatibilityscore + "&";
      }
      if(userFilter.minimumheight) {
        query = query + "minheight=" + userFilter.minimumheight + "&";
      }
      if(userFilter.maximumheight) {
        query = query + "maxheight=" + userFilter.maximumheight + "&";
      }
      if(userFilter.distanceinkm && userFilter.currentlatitude && userFilter.currentlongitude) {
        query = query + "distanceinkm=" + userFilter.distanceinkm + "&inquirerlongitude=" +
        userFilter.currentlongitude + "&inquirerlatitude=" + userFilter.currentlatitude + "&";
        if(userFilter.distanceinkm === 30) {
          query = query + "distancelowerbound=true";
        } else {
          query = query + "distancelowerbound=false";
        }
      }      
    }
    return query;
  }

  /**
   * Build the angular http params required by user finder service.
   * @param userFilter The filters selected by the user.
   */
  buildUserFinderHttpParams(userFilter:UserFilter): HttpParams {
    var isfirstset = false;
    let params = new HttpParams();
    
    if(userFilter != null) {
      if(userFilter.hasphoto) {
        params.append("hasphoto",userFilter.hasphoto);
      }
      if(userFilter.incontact) {
        params.append("incontact",userFilter.incontact);
      }
      if(userFilter.isfavourite) {
        params.append("isfavourite", userFilter.isfavourite);
      }
      if(userFilter.minimumage != null || userFilter.minimumage != undefined) {
        params.append("minage","" + userFilter.minimumage);
      }
      if(userFilter.maximumage != null || userFilter.maximumage != undefined) {
        params.append("maxage","" + userFilter.maximumage);
      }
      if(userFilter.mincompatibilityscore) {
        params.append("mincompatibilityscore","" + userFilter.mincompatibilityscore);
      }
      if(userFilter.maxcompatibilityscore) {
        params.append("maxcompatibilityscore", "" +  userFilter.maxcompatibilityscore);
      }
      if(userFilter.minimumheight) {
        params.append("minheight","" + userFilter.minimumheight);
      }
      if(userFilter.maximumheight) {
        params.append("maxheight","" + userFilter.maximumheight);
      }
      if(userFilter.distanceinkm && userFilter.currentlatitude && userFilter.currentlongitude) {
        params.append("distanceinkm","" + userFilter.distanceinkm); 
        params.append("inquirerlongitude", "" + userFilter.currentlongitude);
        params.append("inquirerlatitude", "" + userFilter.currentlatitude);
        if(userFilter.distanceinkm === 30) {
          params.append("distancelowerbound","true");
        } else {
          params.append("distancelowerbound","false");
        }
      }      
    }
    return params;
  }

  /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    console.log(message);
    //this.messageService.add('HeroService: ' + message);
  }

}
