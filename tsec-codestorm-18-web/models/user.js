// * MODULE DEPENDENCIES
const bcrypt = require('bcrypt');
const mongoose = require('mongoose');

const { Post } = require('./post');

const userSchema = new mongoose.Schema({
    employID: {
        type: Number,
        unique: true,
        sparse: true,
    },
    name: String,
    email: {
        type: String,
        unique: true,
        sparse: true
    },
    password: String,
    status: String,
    DOB: { type: Date, default: new Date (), },
    aniversaryDate: { type: Date, default: new Date (), },
    joiningDate: { type: Date, default: new Date (), },
    familyEmails: [String],
    department: String,
    position: Number,
    fcmToken: String,
});
/**
 * Password hash middleware.
 */
userSchema.pre('save', function(next) {
    var user = this;
    // only hash the password if it has been modified (or is new)
    if (!user.isModified('password')) return next();
    // generate a salt
    bcrypt.genSalt(10, function(err, salt) {
        if (err) return next(err);
        // hash the password using our new salt
        bcrypt.hash(user.password, salt, function(err, hash) {
            if (err) return next(err);
            // override the cleartext password with the hashed one
            user.password = hash;
            next();
        });
    });
});

/**
 * Helper method for validating user's password.
 */
userSchema.methods.comparePassword = function comparePassword (candidatePassword) {
    return new Promise((resolve, reject) => {
        bcrypt.compare(candidatePassword, this.password, function(err, isMatch) {
            if (err) reject(err);
            else resolve(isMatch);
        });
    });
};

/**
 * Get points.
 */
userSchema.methods.getPoints = function getPoints () {
    return new Promise((resolve, reject) => {
        Post.findAll({ user: this._id }, function (err, list) {
            if (err) return err;
            list = list.map(post => {
                let p = 0;
                post.votes.forEach(e => {
                    if (e.type === 'up')
                        p = p + 1;
                });
                resolve (p);
            });
            resolve (list.reduce((a, b) => a + b, 0));
        });
    });
}

const User = mongoose.model('User', userSchema);

// * EXPORT
module.exports = {
    userSchema,
    User,
};