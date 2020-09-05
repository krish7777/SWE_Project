const router = require('express').Router();
const bcrypt = require('bcryptjs');
const isAuth = require('../middlewares/isAuth');

const { getDonor, uploadDonorProfilePic } = require('../controllers/donor');


router.post('/upload-profile-pic', isAuth, uploadDonorProfilePic)
router.get('/get-details', isAuth, getDonor)

const OrganisationModel = require('../models/organisation')
const Donationmodel = require('../models/donation')
const Donormodel = require('../models/donor')

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

	}).limit(15)

})

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


router.post('/makedonation',async (req,res)=>{

	console.log(" this is making donation now")
	const {id,description,latitude,longitude} = req.body
	console.log(id)
	console.log(description)
	console.log(latitude)
	console.log(longitude)

	Donormodel.findById(id).then((donor)=>{
		if(!donor)
			throw new Error()
			var donation = new Donationmodel({
					donor: donor._id,
					description:description,
					latitude:latitude,
					longitude:longitude

			})

			donation.save((err,donation)=>{
				if(err){
					req.flash("error",err.message)
				}
				else {
					res.status(400).json(donation)
				}
			})

	}).catch((e)=>{
		console.log("not working!!!")
	})

})
module.exports = router
