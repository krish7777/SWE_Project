const router = require('express').Router();

const isAuth = require('../middlewares/isAuth');

const { getOrganisation, uploadOrganisationProfilePic } = require('../controllers/organisation');


router.route('/').get((req, res) => {
	res.send("Working fine");
	res.status(400);
})


router.post('/upload-profile-pic', isAuth, uploadOrganisationProfilePic);

router.get('/get-details', isAuth, getOrganisation);




module.exports = router
