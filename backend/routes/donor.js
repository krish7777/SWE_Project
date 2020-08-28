const router = require('express').Router();

const isAuth = require('../middlewares/isAuth');

const { getDonor, uploadDonorProfilePic } = require('../controllers/donor');


router.post('/upload-profile-pic', isAuth, uploadDonorProfilePic)

router.get('/get-details', isAuth, getDonor)

const OrganisationModel = require('../models/organisation')

router.post('/nearbyorganisations', (req, res) => {
	console.log("lets give the nearby organisation")


	OrganisationModel.find({}, (err, organisation) => {
		if (err) {
			console.log("error!!!!!")
		}
		else {
			console.log(organisation)
			res.status(200).json({ "organisations": organisation })
		}

	}).limit(2)

})

module.exports = router
