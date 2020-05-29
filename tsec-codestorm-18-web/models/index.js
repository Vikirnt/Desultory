const User = require('./user');
const Event = require('./event');
const Post = require('./post');

module.exports = {
    ...User,
    ...Post,
    ...Event,
};
