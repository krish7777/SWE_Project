const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const donorSchema = new Schema({
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
    contactNumber: {
        type: Number,
        required: true
    },
    peopleFed: Number,
    moneyRaised: Number,
    donationsMade: [{
        type: Schema.Types.ObjectId,
        ref: 'Donation'
    }]
    //ADDITIONAL FIELDS CAN BE ADDED BELOW
});

module.exports = mongoose.model('Donor', donorSchema);
