const router = require('express').Router();
const DonorModel = require('../models/donor')
const OrganisationModel = require('../models/organisation')

const isAuth = require('../middlewares/isAuth');

router.post('/nearbyorganisations', (req,res)=>{
	console.log("lets give the nearby organisation")

		OrganisationModel.find({},(err,organisation)=>{
			if(err){
			console.log("error!!!!!")}
			else{
				console.log(organisation)
				res.status(200).json({"organisations": organisation})
			}

		}).limit(2)

})

module.exports = router
