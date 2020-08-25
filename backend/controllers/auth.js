const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const Donor = require('../models/donor');
const Organisation = require('../models/organisation')
exports.registerDonor = async (req, res, next) => {
    console.log(req.body);
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

exports.registerOrganisation = async (req, res, next) => {
    let { email, name, password, contactNumber, address, latitude, longitude } = req.body;
    const checkExistingOrganisation = await Organisation.findOne({ email: email })
    if (!checkExistingOrganisation)
        try {
            const hashedPw = await bcrypt.hash(password, 12);

            const organisation = new Organisation({
                email: email,
                password: hashedPw,
                name: name,
                contactNumber: contactNumber,
                address: address,
                latitude: latitude,
                longitude: longitude
            });
            const result = await organisation.save();
            console.log("result", result)
            res.status(201).json({ message: 'Organisation created!', userId: result._id });
        } catch (err) {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        }
    else {
        const error = new Error('Organisation already exists');
        error.statusCode = 401;
        next(error);
    }
};

exports.loginDonor = async (req, res, next) => {

    console.log(req.body);
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

exports.loginOrganisation = async (req, res, next) => {
    const { email, password } = req.body;
    console.log(req.body);
    try {
        const organisation = await Organisation.findOne({ email: email })
        if (!organisation) {
            const error = new Error('An organisation with this email could not be found')
            error.statusCode = 401;
            throw error;
        }
        const isEqual = await bcrypt.compare(password, organisation.password);
        if (!isEqual) {
            const error = new Error('Wrong password!');
            error.statusCode = 401;
            throw error;
        }
        const token = jwt.sign({
            email: organisation.email,
            userId: organisation._id.toString(),
            role: 'Organisation'
        },
            'secret',
            { expiresIn: '24h' }
        );
        res.status(200).json({ token: token, userId: organisation._id.toString() })
    } catch (err) {
        if (!err.statusCode) {
            err.statusCode = 500;
        }
        next(err)
    }
}
