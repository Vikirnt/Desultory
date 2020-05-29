const mongoose = require("mongoose");
const Schema = mongoose.Schema;
const mongoosePaginate = require('mongoose-paginate');

const { userSchema } = require("./user");

const eventSchema = new Schema({
  message: String,
  user: { type: Schema.Types.ObjectId, ref: 'User' },
  eventDate: Date,
  postDate: { type: Date, default: new Date () },
});

// Pagination.
eventSchema.plugin(mongoosePaginate);

const Event = mongoose.model("Event", eventSchema);

exports.Event = Event;
exports.eventSchema = eventSchema;
