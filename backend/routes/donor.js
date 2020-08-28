const router = require('express').Router();
const isAuth = require('../middlewares/isAuth');

const { getDonor, uploadDonorProfilePic } = require('../controllers/donor');


router.post('/upload-profile-pic', isAuth, uploadDonorProfilePic)

router.get('/get-details', isAuth, getDonor)


module.exports = router
