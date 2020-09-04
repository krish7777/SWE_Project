const router = require('express').Router();

const isAuth = require('../middlewares/isAuth');

const { getDonor, uploadDonorProfilePic, getNearbyOrganisations } = require('../controllers/donor');


router.post('/upload-profile-pic', isAuth, uploadDonorProfilePic)

router.get('/get-details', isAuth, getDonor)

const OrganisationModel = require('../models/organisation')
const Donationmodel = require('../models/donation')
const Donormodel = require('../models/donor')

router.post('/nearbyorganisations', isAuth, getNearbyOrganisations)


router.post('/makedonation', isAuth, async (req, res) => {

	console.log(" this is making donation now")
	const { description, latitude, longitude } = req.body
	const id = req.userId;
	console.log(id)
	console.log(description)
	console.log(latitude)
	console.log(longitude)

	Donormodel.findById(id).then((donor) => {
		if (!donor)
			throw new Error()
		var donation = new Donationmodel({
			donor: donor._id,
			description: description,
			latitude: latitude,
			longitude: longitude,
			location: {
				type: "Point",
				coordinates: [longitude, latitude]
			}
		})

		donation.save((err, donation) => {
			if (err) {
				req.flash("error", err.message)
			}
			else {
				res.status(400).json(donation)
			}
		})

	}).catch((e) => {
		console.log("not working!!!")
	})

})

// router.get('/recent-donations',isAuth , )

module.exports = router
