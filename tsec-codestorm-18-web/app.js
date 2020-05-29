// * MODULE DEPENDENCIES
const express = require('express');
const logger = require('morgan');
const chalk = require('chalk');

const mongoose = require('mongoose');

const passport = require('passport'); 
const passportConfig = require('./config/passport');

const userRouter = require('./routes/userRouter');
const postRouter = require('./routes/postRouter');
const eventRouter = require('./routes/eventRouter');

const schedule = require('./util/schedular');

const { jwtSecret } = require('./config');

// * APP CREATION
const app = express();

// * APP CONFIG
// app.set('host', process.env.HOST || '127.0.0.1');
app.set('port', process.env.PORT || 8000);
mongoose.connect(process.env.MONGO_URL, { useNewUrlParser: true });

// * MIDDLEWARE
app.use(logger('dev'));
app.use(express.json());

app.use(passport.initialize());

// * ROUTES

app.get('/', (req, res) => {
    res.send('WORKING!');
});
app.use('/user', userRouter);
app.use('/post/', (req, res, next) => {
    console.log(req.body);
    next();
},passport.authenticate('jwt'), passportConfig.isAuthenticated, postRouter);
app.use('/event/', passport.authenticate('jwt'), passportConfig.isAuthenticated, eventRouter);

// * OTHERS

schedule();

// * EXPORT
module.exports = app;
