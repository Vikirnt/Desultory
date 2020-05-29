// * MODULE DEPENDENCIES
const {
    Post,
} = require("../models/post");
const getSentiment = require("../util/sentimental");
const PAGELIMIT = 20;

// // ? get /post/list/ << no :page
// exports.getPostsByDefault = (req, res) => {
//     req.params.page = 1;
//     return getPosts(req, res);
// }

// ? get /post/list/:page
exports.getPosts = (req, res) => {
    let page = Number(req.params.page);

    Post.paginate({}, {
        page,
        limit: PAGELIMIT,
        populate: 'user'
    }, (err, list) => {
        if (err) return res.send(err);
        res.json(list);
    });
}

// ? get /post/:id
exports.getSinglePost = (req, res) => {
    let id = req.params.id;

    Post.findById(id)
        .populate('user')
        .exec((err, post) => {
            if (err) return res.json(err).status(500);
            if (!post) return res.json({
                error: 'Post not found!',
            }).status(404);
            else return res.json(post);
        });
}

// ? post /post/new
exports.postNew = (req, res) => {
    let data = {};
    data.content = req.get('content');
    data.user = req.user._id;

    let p = new Post(data);
    p.save();
    return res.json(p);
}

// ? post /post/:id/delete
exports.deletePost = (req, res) => {
    let id = req.params.id;
    Post.findByIdAndRemove(id)
        .populate('user')
        .exec((err, result) => {
            if (err) {
                console.error(err);
                return res.json(err).status(500);
            } else return res.json(result);
        });
}

// ? post /post/:id/update
exports.updatePost = (req, res) => {
    let id = req.params.id;
    let content = req.get('content');
    Post.findByIdAndUpdate(id, {
            content
        })
        .exec((err, result) => {
            if (err) return res.json(err).status(500);
            else return res.json(result);
        })
}

// ? post /post/:id/like
exports.likePost = (req, res) => {
    let id = req.params.id;
    Post.findById(id, (err, post) => {
        if (err) return res.json(err).status(500);
        if (!post) return res.json({
            error: 'Post not found!'
        }).status(404);

        let votes = post.votes;
        if (!votes) votes = [];
        if (!votes.some(e => e.actor.equals(req.user._id))) {
            votes.push({
                type: 'up',
                actor: req.user._id,
            });
        }
        votes.push();

        Post.findByIdAndUpdate(id, { votes }, (err, result) => {
            if (err) return res.json(err).status(500);
            else return res.json(result);
        });
    });
}

// ? post /post/:id/dislike
exports.dislikePost = (req, res) => {
    let id = req.params.id;
    Post.findById(id, (err, post) => {
        if (err) return res.json(err).status(500);
        if (!post) return res.json({
            error: 'Post not found!'
        }).status(404);

        let votes = post.votes;
        if (!votes) votes = [];
        if (!votes.some(e => e.actor.equals (req.user._id))) {
            votes.push({
                type: 'down',
                actor: req.user._id,
            });
        }
        votes.push();

        Post.findByIdAndUpdate(id, { votes }, (err, result) => {
            if (err) return res.json(err).status(500);
            else return res.json(result);
        });
    });
}

// ? post /post/:id/unvote
exports.unvotePost = (req, res) => {
    let id = req.params.id;
    Post.findById(id, (err, post) => {
        if (err) return res.json(err).status(500);
        if (!post) return res.json({
            error: 'Post not found!',
        }).status(404);

        let votes = post.votes;
        if (!votes) votes = [];
        for (let i of votes) {
            if (i.actor.equals(req.user._id))
                votes.pop(i)
        }

        Post.findByIdAndUpdate(id, { votes }, (err, result) => {
            if (err) return res.json(err).status(500);
            else return res.json(result);
        });
    });
}

// ? post /post/:id/comments/new
exports.commentOnPost = (req, res) => {
    let id = req.params.id;
    let message = req.get('comment');
    let user = req.user._id;
    Post.findById(id, (err, post) => {
        if (err) return res.json(err).status(500);
        if (!post) return res.json({
            error: 'Post not found!',
        });
        
        let comments = post.comments;
        getSentiment(message)
        .then(sentiment => {
            console.debug(sentiment);

            comments.push({
                user,
                message,
                sentiment,
            });
    
            Post.findByIdAndUpdate(id, { comments }, (err, result) => {
                if (err) return res.json(err).status(500);
                else return res.json(result);
            });

            return next();
        })
        .catch(e => {
            console.error(e);
            return next(e);
        });
    });
}

// ? post /post/:postId/comments/delete/:commendId
exports.deleteComment = (req, res) => {
    let pid = req.params.postId;
    let cid = req.params.commentId;
    Post.findById(pid, (err, post) => {
        if (err) return res.json(err).status(500);
        if (!post) return res.json({
            error: 'Post not found!',
        });
        
        let comments = post.comments;
        for (let comment of comments) {
            if (comment._id.equals(cid))
                comments.pop(comment);
        }

        Post.findByIdAndUpdate(pid, { comments }, (err, result) => {
            if (err) return res.json(err).status(500);
            else return res.json(result);
        });
    });
}
