const router = require('express').Router();
const DonorModel = require('../models/donor.model')

router.route('/').get((req,res)=>{
	res.send("Working fine");
	res.status(400);
})
router.route('/create').get((req,res)=>{
	var newdonor  = new DonorModel({
		name:"Arpit",	
		pswd:"arpit",
	})
	newdonor.save((err,event)=>{
		if(err){
			res.send("ERRRORRR While creating profile")
			res.send(404)
		
		}
		
		res.redirect('/donor/');
		
	})
})


module.exports = router
