const router = require('express').Router();
const bcrypt = require('bcryptjs');
const isAuth = require('../middlewares/isAuth');

const { getDonor, uploadDonorProfilePic, getNearbyOrganisations, getLeaderboard, makeDonation } = require('../controllers/donor');


router.post('/upload-profile-pic', isAuth, uploadDonorProfilePic)
router.get('/get-details', isAuth, getDonor)

const OrganisationModel = require('../models/organisation')
const Donationmodel = require('../models/donation')
const Donormodel = require('../models/donor')

router.post('/nearbyorganisations', isAuth, getNearbyOrganisations)

router.get('/leaderboard', getLeaderboard)


router.post('/makedonation', isAuth, makeDonation)

router.post('/update',async (req,res)=>{
	console.log("this is the update ")
	console.log(req.body)
	const {id,email,password,name,contactNumber,latitude,longitude} = req.body

	console.log(id)

	Donormodel.findById(id,async (err,donor)=>{
		if(err)
			console.log("error in updating")

				donor.email = email
				donor.password = password
				donor.name= name
				donor.contactNumber= contactNumber
				donor.longitude = longitude
				donor.latitude = latitude
			await donor.save((err)=>{
				if(err)
					console.log(err)
				console.log(donor)
				res.status(201).json({"messsage":"updates saved"})
		})

	})


})

module.exports = router
