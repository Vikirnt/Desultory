const express = require('express');
const router = express.Router();

const passport = require('passport');
const passportConfig = require('../config/passport');

const userController = require('../controllers/userController');

router.get('/', passport.authenticate('jwt'), passportConfig.isAuthenticated, userController.getUserDetails);
router.post('/', passport.authenticate('jwt'), passportConfig.isAuthenticated, userController.getUserDetails);
router.post('/fcm', passport.authenticate('jwt'), passportConfig.isAuthenticated, userController.getFcmToken);

router.get('/points', userController.getPoints);
router.post('/points', userController.getPoints);
router.post('/login', userController.generateToken);
// router.post('/signup', userController.postSignup);

module.exports = router;
