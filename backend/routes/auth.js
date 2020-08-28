const express = require('express')

const { registerDonor, registerOrganisation, loginDonor, loginOrganisation } = require('../controllers/auth');
const isAuth = require('../middlewares/isAuth');

const router = express.Router();

router.post('/register/donor', registerDonor)
router.post('/register/organisation', registerOrganisation)
router.post('/login/donor', loginDonor)
router.post('/login/organisation', loginOrganisation)

router.post('/isAuth', isAuth, (req, res) => {//FOR ANY AUTHENTICTED ROUTE, just add the isAuth Middleware.
    console.log("Working Great!!!");

    res.status(200).json({ "role": req.role, "id": req.userId });

})

module.exports = router
