// * MODULE DEPENDENCIES
const {
    User,
    Post,
    Event,
} = require('../models');

const jwt = require('jwt-simple');
const {
    jwtSecret
} = require('../config/variables');

// ? post /login/
exports.generateToken = function generateToken(req, res) {
    let employID = req.get('employID');
    let password = req.get('password');
    if (employID && password) {
        User.findOne({
            employID
        }, (err, user) => {
            if (err) return res.status(500).send(err);
            if (!user) return res.status(401).json({
                error: 'User not found!'
            });

            user.comparePassword(password)
                .then(match => {
                    if (match) {
                        let payload = {
                            id: user._id
                        };
                        let token = jwt.encode(payload, jwtSecret);
                        return res.json({
                            token
                        });
                    } else {
                        return res.status(401).json({
                            error: 'Password incorrect!'
                        });
                    }
                })
                .catch(e => {
                    console.error(e);
                    res.json(e).status(500);
                });
        });
    } else {
        res.sendStatus(401);
    }
}

// ? get/post /
exports.getUserDetails = function (req, res) {
    let user = req.user;
    return res.json(user);
}

// ? post /fcm/
exports.getFcmToken = function (req, res) {
    let fcmToken = req.get ('fcm_token');
    let user = req.user;
    User.findByIdAndUpdate(user._id, {
        fcmToken
    }, (err, result) => {
        if (err) return res.send(err).status(500);
        else if (!user) return res.json({
            error: 'What???'
        }).status(404);
        else return res.json(result);
    });
}

// ? get/post /points
exports.getPoints = function (req, res) {
    let user = req.user;
    user.getPoints()
        .then(p => {
            res.send({
                points: p
            });
        })
        .catch(e => {
            console.error(e);
            res.send(e).status(500);
        });
}

// // ? get/post /posts/
// exports.getPosts = function (req, res) {
//     Post.findAll({ user: req.user._id });
// }

// // ? post /signup/
// exports.postSignup = (req, res) => {
//     const params = [''];

//     const errors = req.validationErrors();

//     if (errors) {
//       req.flash('errors', errors);
//       return res.redirect('/signup');
//     }

//     const user = new User({
//       email: req.body.email,
//       password: req.body.password
//     });

//     User.findOne({ email: req.body.email }, (err, existingUser) => {
//       if (err) { return next(err); }
//       if (existingUser) {
//         req.flash('errors', { msg: 'Account with that email address already exists.' });
//         return res.redirect('/signup');
//       }
//       user.save((err) => {
//         if (err) { return next(err); }
//         req.logIn(user, (err) => {
//           if (err) {
//             return next(err);
//           }
//           res.redirect('/');
//         });
//       });
//     });
//   };