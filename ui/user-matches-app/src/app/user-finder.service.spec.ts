import { TestBed, async, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { UserFinderService } from './user-finder.service';
import { UserFilter } from './user-filter'
import { Result } from './result';

describe('UserFinderService', () => {
  let userFinderService: UserFinderService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserFinderService]
    });
    userFinderService = TestBed.get(UserFinderService);
    httpMock = TestBed.get(HttpTestingController);
  });

  it('should be created', inject([UserFinderService], (service: UserFinderService) => {
    expect(service).toBeTruthy();
  }));

  it('should return a list of two users', (done) => {
    var userFilter = new UserFilter();
    userFilter.incontact = "false";
    userFinderService.getUsers(userFilter)
                   .subscribe(res => {
                     expect(res).toEqual(
                       [
                         {
                           message: "hola", 
                           userdata: [
                             {
                               id: "123",
                               displayname: "Fernando",
                               age: 23,
                               compatabilityScore: 23,
                               contactsExchanged: 1,
                               favourite: true,
                               height: 180,
                               jobtitle: "engineer",
                               mainphoto: "/uri/123.png",
                               religion: "catholism"
                             }
                          ]
                        }
                       ]
                     );
                     done();
                   });

    let searchRequest = httpMock.expectOne('http://localhost:8080/userfinder?incontact=false&');
    
    httpMock.verify();
    searchRequest.flush([ Object({ message: 'hola', userdata: [ Object({ id: '123', displayname: 'Fernando', age: 23, compatabilityScore: 23, contactsExchanged: 1, favourite: true, height: 180, jobtitle: 'engineer', mainphoto: '/uri/123.png', religion: 'catholism' }) ] }) ]);

    httpMock.verify();
  });

  it('should create complete query params', inject([UserFinderService], (service: UserFinderService) => {
    // given user filter to search in user finder api.
    var userFilter = new UserFilter();
    userFilter.hasphoto = "true";
    userFilter.isfavourite = "false";
    userFilter.incontact = "false";
    userFilter.minimumage = 23;
    userFilter.maximumage = 28;
    userFilter.minimumheight = 170;
    userFilter.maximumheight = 185;
    userFilter.mincompatibilityscore = 23;
    userFilter.maxcompatibilityscore = 50;
    userFilter.distanceinkm = 30;
    userFilter.currentlatitude = 1.3456;
    userFilter.currentlongitude = 0.1234;
    
    var urlwithqueryparams = service.buildUserFinderQueryParams(userFilter);

    var expectedresult = "?hasphoto=true&incontact=false&isfavourite=false&minage=23&maxage=28&mincompatibilityscore=23&maxcompatibilityscore=50&minheight=170&maxheight=185&distanceinkm=30&inquirerlongitude=0.1234&inquirerlatitude=1.3456&distancelowerbound=true";

    expect(expectedresult).toBe(urlwithqueryparams);
  }));

  it('should create an empty query param', inject([UserFinderService], (service: UserFinderService) => {
    // given user filter to search in user finder api.
    var userFilter = new UserFilter();
    
    var urlwithqueryparams = service.buildUserFinderQueryParams(userFilter);

    var expectedresult = "?";

    expect(expectedresult).toBe(urlwithqueryparams);
  }));

  it('should create incontact query param', inject([UserFinderService], (service: UserFinderService) => {
    // given user filter to search in user finder api.
    var userFilter = new UserFilter();
    userFilter.incontact = "false";
    
    var urlwithqueryparams = service.buildUserFinderQueryParams(userFilter);

    var expectedresult = "?incontact=false&";

    expect(expectedresult).toBe(urlwithqueryparams);
  }));

  it('should create hasphoto and isfavourite query params', inject([UserFinderService], (service: UserFinderService) => {
    // given user filter to search in user finder api.
    var userFilter = new UserFilter();
    userFilter.hasphoto = "true";
    userFilter.isfavourite = "false";
    
    var urlwithqueryparams = service.buildUserFinderQueryParams(userFilter);

    var expectedresult = "?hasphoto=true&isfavourite=false&";

    expect(expectedresult).toBe(urlwithqueryparams);
  }));

  it('should create distance query params', inject([UserFinderService], (service: UserFinderService) => {
    // given user filter to search in user finder api.
    var userFilter = new UserFilter();
    userFilter.distanceinkm = 30;
    userFilter.currentlatitude = 1.3456;
    userFilter.currentlongitude = 0.1234;
    
    var urlwithqueryparams = service.buildUserFinderQueryParams(userFilter);

    var expectedresult = "?distanceinkm=30&inquirerlongitude=0.1234&inquirerlatitude=1.3456&distancelowerbound=true";

    expect(expectedresult).toBe(urlwithqueryparams);
  }));

  it('should create hasphoto and compatibility score range query params', inject([UserFinderService], (service: UserFinderService) => {
    // given user filter to search in user finder api.
    var userFilter = new UserFilter();
    userFilter.mincompatibilityscore = 23;
    userFilter.maxcompatibilityscore = 50;
    userFilter.hasphoto = "true";
    
    var urlwithqueryparams = service.buildUserFinderQueryParams(userFilter);

    var expectedresult = "?hasphoto=true&mincompatibilityscore=23&maxcompatibilityscore=50&";

    expect(expectedresult).toBe(urlwithqueryparams);
  }));


});



