const Donor = require('../models/donor');
const Organisation = require('../models/organisation')


exports.getDonor = async (req, res, next) => {
    let id = req.userId;
    console.log("hereeeee")
    try {
        const donor = await Donor.findById(id).populate({
            path: "donationsMade",
            select: 'organisationName description peopleFed organisationContact'
        }).select('-latitude -longitude -location -password')
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

exports.getNearbyOrganisations = async (req, res, next) => {
    let id = req.userId;
    try {
        let {
            location:
            { coordinates:
                [longitude, latitude]
            }
        } = await Donor.findById(id).select('location')

        console.log(latitude, longitude)

        let organisations = await Organisation.
            aggregate().
            near({
                near: {
                    type: "Point",
                    coordinates: [longitude, latitude]
                },
                distanceField: 'distance'
            }).sort('distance')

        // find({
        //     location: {

        //         $nearSphere: {

        //             $geometry: {
        //                 type: "Point",
        //                 coordinates: [longitude, latitude]
        //             }

        //         }

        //     }
        // })

        console.log(organisations)

        res.json({
            "organisations": organisations
        })

    } catch (err) {
        if (!err.statusCode) {
            err.StatusCode = 500;
        }
        next(err);
    }
}

exports.getLeaderboard = async (req, res, next) => {
    try {
        const donors = await Donor.find({}).select('name email peopleFed').sort('-peopleFed').limit(10)
        res.status(201).json({ "donors": donors })
    } catch (err) {
        if (!err.statusCode) {
            err.statusCode = 500;
        }
        next(err);
    }
}