const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const organisationSchema = new Schema({
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
    address: {
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
    donationsReceived: [{
        type: Schema.Types.ObjectId,
        ref: 'Donation'
    }],
    //ADDITIONAL FIELDS CAN BE ADDED BELOW
});

module.exports = mongoose.model('Organisation', organisationSchema);
