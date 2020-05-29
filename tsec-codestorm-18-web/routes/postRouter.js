const express = require('express');
const router = express.Router();

const passportConfig = require('../config/passport');

const postController = require('../controllers/postController');

router.post('/new', postController.postNew); // C

router.get('/:id/', postController.getSinglePost); // R
router.get('/list/:page', postController.getPosts);
router.post('/:id/', postController.getSinglePost);
router.post('/list/:page', postController.getPosts);
// router.get('/list', postController.getPostsByDefault);

router.post('/:id/update', postController.updatePost); // U

router.post('/:id/delete', postController.deletePost); // D

router.post('/:id/like', postController.likePost);
router.post('/:id/dislike', postController.dislikePost);
router.post('/:id/unvote', postController.unvotePost);

router.post('/:id/comments/new', postController.commentOnPost);
router.post('/:postId/comments/delete/:commentId', postController.deleteComment);

module.exports = router;
