const express = require('express')

const { registerDonor, registerNgo, loginDonor, loginNgo } = require('../controllers/auth');
const isAuth = require('../middlewares/isAuth');

const router = express.Router();

router.post('/register/donor', registerDonor)
router.post('/register/ngo', registerNgo)
router.post('/login/donor', loginDonor)
router.post('/login/ngo', loginNgo)

router.post('/secret', isAuth, (req, res) => {
    res.send(req.role)
})

module.exports = router