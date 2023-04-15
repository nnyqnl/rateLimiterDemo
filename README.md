
## Run Project

Download the code from github: https://github.com/nnyqnl/rateLimiterDemo.git

Open with Intellij IDEA or Eclipse

Run RateLimiterDemoApplication after the project build finished

## Test Api

#### Login with username and password

~~~
request
localhost:8080/login?username=xiaoming&password=123
method: GET


response
{
    "token": "4d83c3a0-a914-42a0-aee9-645cd3d5bed4"
}
~~~


#### Test Rate limite Api, the "Authorization" is "Bearer Token", and token received from "Login Api"
~~~
request
localhost:8080/rateLimiter/test
header 'Authorization: Bearer 4d83c3a0-a914-42a0-aee9-645cd3d5bed4'


response
test success!
~~~

This api allow 5 requests/minute. If the number of requests you make exceeds that limit, then an error ("Too Many Requests") will be triggered.
