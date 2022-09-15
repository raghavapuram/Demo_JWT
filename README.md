# Demo_JWT

#How to use of the Application
###  1. Create user
      URL: http://localhost:8080/user/save
      type: POST
      Sample Body:{
                   "name": "name5",
                    "pass": "pass6",
                    "rolls": ["USER",
                     "ADMIN"]
                    }
                    
### 2. Get Token of user
    URL: http://localhost:8080/user/login
    type: POST
    Sample Body: {
                 "name": "name5",
                 "pass": "pass6"
                }
                
###  3. Use token for other requests
 ####     a. create Car
         URL: http://localhost:8080/cars/
         Type: POST
         Header: Authorization // provide token
         Body: {
                "name": "ssssa",
                "model": "model2",
                 "mfDate": "2022-07-01"
             }
 
