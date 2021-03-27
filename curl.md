**The curl requests for MealRestController:**

1. **Get by id:** 
              *curl localhost:8090/topjava/rest/meals/{id}*
2. **Delete by id:** 
              *curl -X DELETE localhost:8090/topjava/rest/meals/{id}*
3. **Get all:** 
              *curl localhost:8090/topjava/rest/meals*
4. **Create with location:** 
              *curl -X POST localhost:8090/topjava/rest/meals -H 'Content-type:application/json' -d '{"dateTime":"2021-01-01T00:00:00","description":"Some descriptiomn","calories":10}'* 
5. **Update:** 
              *curl -X PUT localhost:8090/topjava/rest/meals/{id} -H 'Content-type:application/json' -d '{"dateTime":"2021-01-31T22:00:00","description":"Update","calories":50}'*
6. **Get between:**
              *curl "localhost:8090/topjava/rest/meals/filter?startDate=2020-01-30&startTime=08:30:30&endDate=2020-01-30&endTime=22:30:30" (for macOs you have to use quotes with URL)*
