// * MODULE DEPENDENCIES
const {
    Event
} = require("../models/event");

const PAGELIMIT = 20;

// // ? get /event/list/ << no :page
// exports.getEventsByDefault = (req, res) => {
//     req.params.page = 1;
//     return getEvents(req, res);
// }

// ? get /event/list/:page
exports.getEvents = (req, res) => {
    let page = Number(req.params.page);

    Event.paginate({},{ page, limit: PAGELIMIT, populate: 'user' }, (err, list) => {
        if (err) return res.send(err);
        res.json(list);
    });
}

// ? get /event/:id
exports.getSingleEvent = (req, res) => {
    let id = req.params.id;

    Event.findById(id)
        .populate('user')
        .exec((err, event) => {
            if (err) return res.json(err).status(500);
            if (!event) return res.json({
                error: 'Event not found!'
            }).status(404);
            else return res.json(event);
        });
}

// ? post /event/new
exports.eventNew = (req, res) => {
    let { message, eventDate } = { message: req.get('message'), eventDate: req.get('eventDate') };
    data = {
        user: req.user._id,
    };
    if (message) data.message = message;
    if (eventDate) data.eventDate = eventDate;

    let p = new Event(data);
    p.save();
    return res.json(p);
}

// ? post /event/:id/delete
exports.deleteEvent = (req, res) => {
    let id = req.params.id;
    Event.findByIdAndRemove(id)
        .populate('user')
        .exec((err, result) => {
            if (err) {
                console.error(err);
                return res.json(err).status(500);
            } else return res.json(result);
        });
}

// ? post /event/:id/update
exports.updateEvent = (req, res) => {
    let id = req.params.id;
    let data = req.body;
    Event.findByIdAndUpdate(id, data)
        .exec((err, result) => {
            if (err) return res.json(err).status(500);
            else return res.json(result);
        })
}
