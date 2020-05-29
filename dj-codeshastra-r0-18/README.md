Automatic Toll Payment System
=============================
Built for DJSCE CSI Codeshastra 4.0
-----------------------------------
###### \#hackathon
------------------

This is the backend server made for college hackathon round 0 (6 hours) on 28th Jan 2018 from time 0830 to 1430.

## Warning:

> This is a terribly written application and I have published it so that I understand what NOT to do.
> People on the internet are not posting thier failures. We should learn from failures.
> You can go through what went wrong in this note: [FAILURES.md](/FAILURES.md)

### Concept:
1. User is driving a car.
2. User is in a set range of a toll booth.
3. User gets a notification on their phone via our Application.
4. TollApplication asks User whether they wish to pay before they queue at the toll booth.
5. User authorises payment.
6. TollApplication has been authorised for automatic payments.
7. TollApplication deducts toll as per the toll booth's specifications (vehicle type, set toll scheme, etc).
8. TollApplication requests server to go through with payment.
9. Server does the payment, gets the invoice number.
10. Server sends a QR code to TollApplication with a hashed invoice number. This same hash is stored in the server's queue database table.
11. User scans this QR code at the toll booth using TollBoothApplication.
12. TollBoothApplication authenticates this hash with the hash database table on server.
13. If authenticated then user goes through the Toll Booth.
14. Hash and QR code are deleted to prevent re-use and fraud.

### Technology stack:
1. **User:** Toll Application (Android: "EasyToll")
2. **Staff:** Toll Booth Application (Android: "EasyTollBooth")
3. **Server:** Django RESTful project.

### Contributors:
1. **Manav Shah**: Data collection and tech support.
2. **Rushabh Shroff**: Android applications.
3. **Vikrant Gajria**: Server side.
4. **Yash Javeri**: Android applications.

-----------
MIT LICENSE
-----------

> Copyright (c) 2018 Vikrant Gajria
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
