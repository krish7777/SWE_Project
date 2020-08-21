const router = require('express').Router();

router.route('/').get((req,res)=>{
	res.send("Working fine");
	res.status(400);
})



module.exports = router
