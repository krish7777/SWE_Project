const express = require('express')

const { registerDonor, registerOrganisation, loginDonor, loginOrganisation } = require('../controllers/auth');
const isAuth = require('../middlewares/isAuth');

const router = express.Router();

router.post('/register/donor', registerDonor)
router.post('/register/organisation', registerOrganisation)
router.post('/login/donor', loginDonor)
router.post('/login/organisation', loginOrganisation)

router.post('/secret', isAuth, (req, res) => {//FOR ANY AUTHENTICTED ROUTE, just add the isAuth Middleware.
    const {id} = req.body;
    
    console.log(id);
    console.log("Working Great!!!");
    res.status(400).send("GREAT JOB");
    
})

module.exports = router
