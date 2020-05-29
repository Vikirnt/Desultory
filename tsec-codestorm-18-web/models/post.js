// * MODULE DEPENDENCIES
const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const mongoosePaginate = require('mongoose-paginate');

const {
    userSchema
} = require('./user');
const getSentiment = require('../util/sentimental');

const voteSchema = new Schema({
    type: {
        type: String,
        required: true,
        enum: ['up', 'down'],
    },
    actor: {
        type: Schema.Types.ObjectId,
        ref: 'User',
    },
});

const commentSchema = new Schema({
    user: { type: Schema.Types.ObjectId, ref: 'User' },
    message: String,
    sentiment: {
        type: Number,
        default: -99,
    },
});

const postSchema = new Schema({
    content: {
        type: String,
        sparse: true,
    },
    user: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        unique: false,
        sparse: true,
    },
    date: {
        type: Date,
        default: new Date(),
    },
    votes: [voteSchema],
    comments: [commentSchema],
});

// Pagination.
postSchema.plugin(mongoosePaginate);

const Post = mongoose.model('Post', postSchema);

// * EXPORT
module.exports = {
    postSchema,
    voteSchema,
    commentSchema,

    Post,
}
