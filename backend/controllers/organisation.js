const Organisation = require('../models/organisation')

exports.getOrganisation = async (req, res, next) => {
    let id = req.userId;
    try {
        const organisation = await Organisation.findById(id).select('-password');
        console.log("res", organisation)
        res.status(200).json(organisation)
    } catch (err) {
        if (!err.statusCode) {
            err.StatusCode = 500;
        }
        next(err)
    }
}

exports.uploadOrganisationProfilePic = async (req, res, next) => {
    let id = req.userId;
    const { profilePicPath } = req.body;
    try {
        const organisation = await Organisation.updateOne({ _id: id }, { $set: { profilePicPath } })
        res.status(201).json({ "updated": "ok" })
    } catch (err) {
        if (!err.statusCode) {
            err.StatusCode = 500;
        }
        next(err);
    }
}