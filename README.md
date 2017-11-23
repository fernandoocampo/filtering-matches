assumptions
- All filters are not required.
- If client doesn't search any filter the system should return nothing. 
- if client doesn't send min or max in the range filters, the system should have the following behavior.
    + if min is set and max not, the service search from min value until any value.
    + if max is set and min not, the service search from the min allowed until the given max value.
- The data of the database contains values that I will considered simulated.
    + contacts_exchanged
    + compatibility_score
- There is not business logic, for that reason a service layer is not required and controller goes directly to dao layer.
- The database used in this service is static and not have mutations capabilities, just reading.
- The database used in this service hypotetically is a robust and well known database in the industry.
- I assume that in Contact filter is if the match is in the contacts of the user that made the search. Then for this filter I will match all objects which contacts_exchanged is greater than 1
- This release only have a database repository and support the current amount of requests.
- I assume to increase the performance of the application the company decided to add an array for localization on city attribute. it has the form: loc: [lon,lat]
