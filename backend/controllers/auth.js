const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const Donor = require('../models/donor');
const Ngo = require('../models/ngo')
exports.registerDonor = async (req, res, next) => {
    const { email, name, password, contactNumber } = req.body;
    const checkExistingDonor = await Donor.findOne({ email: email })
    if (!checkExistingDonor)
        try {
            const hashedPw = await bcrypt.hash(password, 12);

            const donor = new Donor({
                email: email,
                password: hashedPw,
                name: name,
                contactNumber: contactNumber
            });
            const result = await donor.save();
            console.log("result", result)
            res.status(201).json({ message: 'Donor created!', userId: result._id });
        } catch (err) {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        }
    else {
        const error = new Error('Donor already exists');
        error.statusCode = 401;
        next(error);
    }
};

exports.registerNgo = async (req, res, next) => {
    const { email, name, password, contactNumber, address, location } = req.body;
    const checkExistingNgo = await Ngo.findOne({ email: email })
    if (!checkExistingNgo)
        try {
            const hashedPw = await bcrypt.hash(password, 12);

            const ngo = new Ngo({
                email: email,
                password: hashedPw,
                name: name,
                contactNumber: contactNumber,
                address: address,
                location: location
            });
            const result = await ngo.save();
            console.log("result", result)
            res.status(201).json({ message: 'Ngo created!', userId: result._id });
        } catch (err) {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        }
    else {
        const error = new Error('Ngo already exists');
        error.statusCode = 401;
        next(error);
    }
};

exports.loginDonor = async (req, res, next) => {
    const { email, password } = req.body;
    try {
        const donor = await Donor.findOne({ email: email })
        if (!donor) {
            const error = new Error('A donor with this email could not be found')
            error.statusCode = 401;
            throw error;
        }
        const isEqual = await bcrypt.compare(password, donor.password);
        if (!isEqual) {
            const error = new Error('Wrong password!');
            error.statusCode = 401;
            throw error;
        }
        const token = jwt.sign({
            email: donor.email,
            userId: donor._id.toString(),
            role: 'Donor'
        },
            'secret',
            { expiresIn: '24h' }
        );
        res.status(200).json({ token: token, userId: donor._id.toString() })
    } catch (err) {
        if (!err.statusCode) {
            err.statusCode = 500;
        }
        next(err)
    }
}

exports.loginNgo = async (req, res, next) => {
    const { email, password } = req.body;
    try {
        const ngo = await Ngo.findOne({ email: email })
        if (!ngo) {
            const error = new Error('A ngo with this email could not be found')
            error.statusCode = 401;
            throw error;
        }
        const isEqual = await bcrypt.compare(password, ngo.password);
        if (!isEqual) {
            const error = new Error('Wrong password!');
            error.statusCode = 401;
            throw error;
        }
        const token = jwt.sign({
            email: ngo.email,
            userId: ngo._id.toString(),
            role: 'Ngo'
        },
            'secret',
            { expiresIn: '24h' }
        );
        res.status(200).json({ token: token, userId: ngo._id.toString() })
    } catch (err) {
        if (!err.statusCode) {
            err.statusCode = 500;
        }
        next(err)
    }
}