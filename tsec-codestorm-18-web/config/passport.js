const passport = require('passport');

const passportJWT = require("passport-jwt");
const JWTStrategy = passportJWT.Strategy;
const ExtractJWT = passportJWT.ExtractJwt;

const { jwtSecret } = require('./variables');

const {
    User
} = require('../models/user');

passport.serializeUser((user, done) => {
    done(null, user.id);
});

passport.deserializeUser((id, done) => {
    User.findById(id, (err, user) => {
        done(err, user);
    });
});

var jwtOptions = {};
jwtOptions.jwtFromRequest = ExtractJWT.fromAuthHeaderWithScheme('token');
jwtOptions.secretOrKey = jwtSecret;

/**
 * Sign in using JWT.
 */
passport.use(new JWTStrategy(jwtOptions,
    (payload, done) => {
        //find the user in db if needed. This functionality may be omitted if you store everything you'll need in JWT payload.
        return User.findById(payload.id)
            .then(user => {
                return done(null, user);
            })
            .catch(err => {
                return done(err);
            });
    }
));

/**
 * Login Required middleware.
 */
exports.isAuthenticated = (req, res, next) => {
    if (req.isAuthenticated()) return next();
    else res.json({ error: 'Unauthenticated' }).status(403);
};

/**
 * Authorization Required middleware.
 */
exports.isAuthorized = (req, res, next) => {
    const provider = req.path.split('/').slice(-1)[0];
    const token = req.user.tokens.find(token => token.kind === provider);
    if (token) {
        next();
    } else {
        res.redirect(`/auth/${provider}`);
    }
};