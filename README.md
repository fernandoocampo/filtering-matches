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