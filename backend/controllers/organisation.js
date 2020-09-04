const Organisation = require('../models/organisation')
const Donation = require('../models/donation')
const Donor = require('../models/donor')

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

        let donations = await Donation.find({
            location: {

                $nearSphere: {

                    $geometry: {
                        type: "Point",
                        coordinates: [longitude, latitude]
                    }

                }

            },
            accepted: false
        }
        )
        // console.log(donations)

        res.json(donations)
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

    try {

        const donation = await Donation.updateOne({ _id: donation_id }, { $set: { accepted: true, receiver: org_id, peopleFed } })


        console.log(donation)
        // const { donor } = await Donation.findById(id).select('donor')
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