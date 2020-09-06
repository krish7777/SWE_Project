const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let organisationSchema = new Schema({
    email: {
        type: String,
        required: true
    },
    password: {
        type: String,
        required: true
    },
    name: {
        type: String,
        required: true
    },
    description: {
        type: String,
        required: true
    },
    contactNumber: {
        type: Number,
        required: true
    },
    profilePicPath: {
        type: String,
        default: ""
    },
    address: {
        type: String,
        required: true
    },
    peopleFed: {
        type: Number,
        default: 0
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
    donationsReceived: [{
        type: Schema.Types.ObjectId,
        ref: 'Donation'
    }],
    //ADDITIONAL FIELDS CAN BE ADDED BELOW
});

organisationSchema.index({ location: "2dsphere" });


module.exports = mongoose.model('Organisation', organisationSchema);
