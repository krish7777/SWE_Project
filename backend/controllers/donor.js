const Donor = require('../models/donor');

exports.getDonor = async (req, res, next) => {
    let id = req.userId;
    try {
        const donor = await Donor.findById(id).select("-password");
        console.log("res", donor)
        res.status(200).json(donor)
    } catch (err) {
        if (!err.statusCode) {
            err.StatusCode = 500;
        }
        next(err)
    }
}

exports.uploadDonorProfilePic = async (req, res, next) => {
    let id = req.userId;
    const { profilePicPath } = req.body;
    try {
        const donor = await Donor.updateOne({ _id: id }, { $set: { profilePicPath } })
        res.status(201).json({ "updated": "ok" })
    } catch (err) {
        if (!err.statusCode) {
            err.StatusCode = 500;
        }
        next(err);
    }
}