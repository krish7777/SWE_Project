const router = require('express').Router();
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
isAuth = require('../middlewares/isAuth');

const { getOrganisation, uploadOrganisationProfilePic, getNearbyDonations, acceptDonation } = require('../controllers/organisation');

const OrganisationModel = require('../models/organisation')
const Donationmodel = require('../models/donation')
const Donormodel = require('../models/donor')

router.route('/').get((req, res) => {
	res.send("Working fine");
	res.status(400);
})

router.get('/nearby-donations', isAuth, getNearbyDonations)


router.post('/upload-profile-pic', isAuth, uploadOrganisationProfilePic);

router.get('/get-details', isAuth, getOrganisation);

router.post('/update',async (req,res)=>{
	console.log("this is the update ")
	console.log(req.body)
	const {id,email,password,name,description,address,contactNumber,latitude,longitude} = req.body

	console.log(id)

	OrganisationModel.findById(id,async (err,organisation)=>{
		if(err)
			console.log("error in updating")
			organisation.email= email
			organisation.password= await bcrypt.hash(password, 12);
			organisation.name= name
			organisation.description= description
			organisation.contactNumber= contactNumber
			organisation.address= address
			organisation.latitude= latitude
			organisation.longitude= longitude

			await organisation.save((err)=>{
				if(err)
					console.log(err)
				console.log(organisation)
				res.status(201).json({"messsage":"updates saved"})
		})

	})


})

router.post('/accept-donation', isAuth, acceptDonation)



module.exports = router
