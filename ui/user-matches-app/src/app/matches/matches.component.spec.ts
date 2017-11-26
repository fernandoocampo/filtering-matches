import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // <-- NgModel lives here
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { By }              from '@angular/platform-browser';

import { MatchesComponent } from './matches.component';
import { UserFilter } from '../user-filter';
import { UserFinderService } from '../user-finder.service';

describe('MatchesComponent', () => {
  let component: MatchesComponent;
  let fixture: ComponentFixture<MatchesComponent>;
  let _tagListEl: HTMLElement;
  let userFinderService: UserFinderService;
  let httpMock: HttpTestingController;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MatchesComponent ],
      imports: [ FormsModule, HttpClientTestingModule ],
      providers: [ UserFinderService ]
    })
    .compileComponents();
    userFinderService = TestBed.get(UserFinderService);
    httpMock = TestBed.get(HttpTestingController);

    fixture = TestBed.createComponent(MatchesComponent);
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
/*
  it('should consume user service', async() => {

    component.model.incontact = "false";   

    component.getUsers();

    let searchRequest = httpMock.expectOne('http://localhost:8080/userfinder?incontact=false&');
    
    httpMock.verify();
    var resultservice = [ Object({ message: 'hola', userdata: [ Object({ id: '123', displayname: 'Fernando', age: 23, compatabilityScore: 23, contactsExchanged: 1, favourite: true, height: 180, jobtitle: 'engineer', mainphoto: '/uri/123.png', religion: 'catholism' }) ] }) ];
    searchRequest.flush(resultservice);

    // The table should exist
    var element = fixture.debugElement.query(By.css('table'));
    var htmlElement = element.nativeElement;
    expect(htmlElement).toBeDefined();

    spyOn(userFinderService, 'getUsers')
    .and.returnValue(resultservice);


    // 2nd change detection displays the async-fetched hero
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      // The first row of the table
      element = fixture.debugElement.query(By.css('tbody'));
      htmlElement = element.nativeElement;
      expect(htmlElement.textContent).toEqual("");
    });

  });
*/
  it('should have a table to display the users', () => {
    const table = fixture.debugElement.query(By.css('table'));
    var htmlElement = table.nativeElement;
    expect(htmlElement.innerHTML).toContain("thead");
  })

  it('should show default Search Form in h1 tag', () => {
    //const fixture = TestBed.createComponent(MatchesComponent);
    const de = fixture.debugElement.query(By.css('h1'));
    expect(de.nativeElement.textContent).toEqual('Search Form');
  });

  it('Form child elements Count', () => {
    _tagListEl = fixture.debugElement.query(By.css('form')).nativeElement;
    fixture.detectChanges();
    expect(_tagListEl.childElementCount).toEqual(10);
  });
  
});
