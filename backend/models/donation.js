const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const donationSchema = new Schema({
    donor: {
        type: Schema.Types.ObjectId,
        ref: 'Donor',
        required: true
    },
    receiver: {
        type: Schema.Types.ObjectId,
        ref: 'Organisation',
    },
    postTime: {
        type: Schema.Types.Date,
        default: Date.now()
    },
    acceptedTime: {
        type: Schema.Types.Date
    },
    peopleFed: {//NAME TO BE DECIDED
        type: Number,

    },
    description: {
        type: String,
        required: true
    },
    latitude: {
        type: Number,
        required: true
    },
    longitude: {
        type: Number,
        required: true
    },
    location: {
        type: { type: String },
        coordinates: [Number]
    },
    accepted: {
        type: Boolean,
        default: false
    },
    donorName: {
        type: String
    },
    organisationName: {
        type: String
    },
    donorContact: {
        type: String
    },
    organisationContact: {
        type: String
    }
    //ADDITIONAL FIELDS CAN BE ADDED BELOW

});

donationSchema.index({ location: "2dsphere" })

module.exports = mongoose.model('Donation', donationSchema);
