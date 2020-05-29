require('../../config');

const mongoose = require("mongoose");
mongoose.connect(process.env.MONGO_URL, { useNewUrlParser: true });

const { User, Event, Post } = require('..');

var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
    let uid = testUser();
    let pid = testPost(uid);
    let eid = testEvent(uid);
    // deleteAll (uid, pid, eid);
});

function testUser () {
    User.create({
        name: "Vikrant Gajria",
        employID: 1000,
        email: "vikrantgajria@company.com",
        password: "pass@123",
        status: "married",
        familyEmails: ["sister1@gmail.com", "brotha2@gmail.com"],
        department: "tech",
        position: 12,
        DOB: new Date("September 30 2000"),
        anniversaryDate: new Date('1 September, 2000'),
        joiningDate: new Date('22 September, 2003'),
    });

    // User.findOne({ employID: 1 }, function (err, user) {
    //     if (user) {
    //         console.log(user);
    //         return user._id;
    //     } else {
    //         return null;
    //     }
    // });
}

function testPost (uid) {
    Post.create({
        content: "I completed the backend.",
        user: mongoose.Types.ObjectId('5ba4fd52cea6e75c81afc5cc'),
    });

    // Post.findOne({ user: '5ba4fd52cea6e75c81afc5cc' })
    //     .populate('user')
    //     .exec(function (err, post) {
    //         if (post) { 
    //             console.log(post);
    //             return post._id;
    //         } else {
    //             return null;
    //         }
    //     });
}

function testEvent (uid) {
    Event.create({
        message: "The art of flirting.",
        user: mongoose.Types.ObjectId('5ba4fd52cea6e75c81afc5cc'),
    });
}

function deleteAll (uid, pid, eid) {
    User.findOneAndDelete({ _id: uid });
    Post.findOneAndDelete({ _id: pid });
}
