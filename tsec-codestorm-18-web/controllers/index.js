const userController = require('./userController');
const postController = require('./postController');
const eventController = require('./eventController');

module.exports = {
    ...userController,
    ...postController,
    ...eventController,
}
