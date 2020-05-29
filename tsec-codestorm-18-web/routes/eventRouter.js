const express = require('express');
const router = express.Router();

const passportConfig = require('../config/passport');

const eventController = require('../controllers/eventController');

router.post('/new', eventController.eventNew); // C

router.get('/:id/', eventController.getSingleEvent); // R
router.get('/list/:page', eventController.getEvents);
// router.get('/list', eventController.getEventsByDefault);

router.post('/:id/update', eventController.updateEvent); // U

router.post('/:id/delete', eventController.deleteEvent); // D

module.exports = router;
