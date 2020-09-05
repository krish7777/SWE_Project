const router = require('express').Router();

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

// router.get('/recent-donations',isAuth , )

module.exports = router
