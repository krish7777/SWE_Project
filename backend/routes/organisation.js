const router = require('express').Router();

const isAuth = require('../middlewares/isAuth');

const { getOrganisation, uploadOrganisationProfilePic, getNearbyDonations, acceptDonation } = require('../controllers/organisation');


router.route('/').get((req, res) => {
	res.send("Working fine");
	res.status(400);
})

router.get('/nearby-donations', isAuth, getNearbyDonations)


router.post('/upload-profile-pic', isAuth, uploadOrganisationProfilePic);

router.get('/get-details', isAuth, getOrganisation);

router.post('/accept-donation', isAuth, acceptDonation)



module.exports = router
