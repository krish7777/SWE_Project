const Organisation = require('../models/organisation')
const Donation = require('../models/donation')
const Donor = require('../models/donor')

exports.getOrganisation = async (req, res, next) => {
    let id = req.userId;
    try {
        const organisation = await Organisation.findById(id).populate({
            path: "donationsReceived",
            select: 'donorName description peopleFed donorContact'
        }).select('-latitude -longitude -location -password')
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

exports.getNearbyDonations = async (req, res, next) => {
    let id = req.userId;
    try {
        let {
            location:
            { coordinates:
                [longitude, latitude]
            }
        } = await Organisation.findById(id).select('location')

        console.log(latitude, longitude)

        let donations = await Donation.
            aggregate()
            .near({
                near: {
                    type: "Point",
                    coordinates: [longitude, latitude]
                },
                distanceField: 'distance'
            })
            .match({
                accepted: false
            })
            .sort('distance')


        // find({
        //     location: {

        //         $nearSphere: {

        //             $geometry: {
        //                 type: "Point",
        //                 coordinates: [longitude, latitude]
        //             }

        //         }

        //     },
        //     accepted: false
        // }
        // )
        console.log(donations)

        res.json({ "donations": donations })
    } catch (err) {
        if (!err.statusCode) {
            err.statusCode = 500;
        }
        next(err)
    }
}

exports.acceptDonation = async (req, res, next) => {
    let org_id = req.userId;
    console.log("STARTINGGGG")
    let { donation_id, donor_id, peopleFed } = req.body;//id is donation id

    console.log(req.body)

    try {

        const org = await Organisation.findById(org_id).select('name contactNumber')
        // const don = await Donor.findById(donor_id).select('name contactNumber')

        const donation = await Donation.updateOne({ _id: donation_id }, { $set: { accepted: true, receiver: org_id, peopleFed, organisationName: org.name, organisationContact: org.contactNumber } })


        console.log(donation)
        await Donor.updateOne({ _id: donor_id }, { $push: { donationsMade: donation_id }, $inc: { peopleFed } }) // update peoplefed
        await Organisation.updateOne({ _id: org_id }, { $push: { donationsReceived: donation_id } })

        res.status(201).json({ "accepted": "ok" })

    } catch (err) {
        if (!err.statusCode) {
            err.statusCode = 500;
        }
        next(err)
    }
}