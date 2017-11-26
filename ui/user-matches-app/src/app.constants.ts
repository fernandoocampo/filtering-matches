import { Injectable } from '@angular/core';

/**
 * f anything changes there, like a version of the api which is 
 * stored in the url or the endpoint/server whatever, I can do 
 * those changes immediatelly at one point.
 */
@Injectable()
export class Configuration {
    public Server = 'http://localhost:8080/';
    public ApiUrl = 'userfinder';
    public ServerWithApiUrl = this.Server + this.ApiUrl;
}