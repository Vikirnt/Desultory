<div align="center">
  <h1>𝍐 Failures</h1>
  The reason why this project didn't work
  <hr>
</div>

1. I spent too much time on Authentication using [JWT](https://jwt.io) though I did not prepare beforehand. It was my first time using JWT. Checkout [Passport-jwt](https://github.com/themikenicholson/passport-jwt). You can refer to this project to see how I configured Passport to use jwt.
Since this was a hackathon I could've spent less time working on non-trivial features.

2. Did not know about [populate](https://mongoosejs.com/docs/populate.html) in mongoose.

3. Did not know how to *not* send the password to client in mongoose.

4. Spent too much time copy-pasting code and writing the same controller functions with different names. I rectified this mistake in 
my next hackathon. Look at the controllers [here](https://github.com/vixrant/kjsce-hackathon-18/tree/master/api/subjects). This can also
be handled using [express-restify-mongoose](https://github.com/florianholzapfel/express-restify-mongoose).

5. Spent a lot of time designing a simple score system.
