Live Local Train Tracking
=============================
Built for Mukesh Patel Hack-n-Code 
-----------------------------------
###### \#hackathon
------------------

This is the backend server made for college hackathon (12 hours) on 31 March 2018 from time 0930 to 2100.

## Warning:

> This is a terribly written application and I have published it so that I understand what NOT to do.
> People on the internet are not posting thier failures. We should learn from failures.
> You can go through what went wrong in this note: [FAILURES.md](/FAILURES.md)

*Application repo:* Removed from my profile

### Ending commit: https://github.com/vixrant/mp-HackNCode-18-web/commit/bfbe79ea5c22bf14d466259b5f6320f1e1fb1c4b

_Any changes after this commit were not made during the Hackathon and are for self improvement and practice only._

### Concept:
1. User wants to know the live timings of local trains in a smart city.
2. User is in a set range for the station.
3. User gets a notification on their phone via our Application.
4. Application asks about the train's details they are sitting in(As this application works on Crowd Sourcing).
5. Some other user can use this information which is uploaded on the server to know the current status of the trains.

### Technology stack:
1. **User:** TrainTracking Application, made in Flutter with cross platform code.
2. **Backend:** Django RESTful project hosted on Heroku.
3. **Database:** Postgresql database on Amazon S3.
4. **Security:** bcrypt encryption for sensitive data.

### Demo:
1. **Backend:** http://traintracking-viki.herokuapp.com/api/
2. **Android app and Recording:** https://drive.google.com/open?id=1q-eqe6aAjxRm8n3UhX2w792pRcSdOmO8

### Contributors:
1. **Rushabh Shroff**:  Android Application using Flutter.
2. **Yash Javeri**:  Android Application using Flutter and Data Collection.
3. **Vikrant Gajria**: RESTful API using Django REST Framework, hosted on Heroku.

### Future prospects:

#### Features:
1. IOT for anonymous location tracking.
2. m-Indicator database sharing.
3. Buy tickets and track trains on the app.

#### Business model:
1. Advertisements.
2. A cut from the tickets bought.

-----------
MIT LICENSE
-----------

> Copyright (c) 2018,2019 Vikrant Gajria
>
> Permission is hereby granted, free of charge, to any person obtaining a copy
> of this software and associated documentation files (the "Software"), to deal
> in the Software without restriction, including without limitation the rights
> to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
> copies of the Software, and to permit persons to whom the Software is
> furnished to do so, subject to the following conditions:
>
> The above copyright notice and this permission notice shall be included in all
> copies or substantial portions of the Software.
>
> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
> IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
> FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
> AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
> LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
> OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
> SOFTWARE.
