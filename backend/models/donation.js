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
    timestamp: {
        type: Schema.Types.Date,
        default: Date.now()
    },
    amount: {//NAME TO BE DECIDED
        type: Number,

    },
    description:{
      type:String,
      required:true
    },
    latitude: {
        type: Number,
        required: true
    },
    longitude: {
        type: Number,
        required: true
    }
    //ADDITIONAL FIELDS CAN BE ADDED BELOW

});

module.exports = mongoose.model('Donation', donationSchema);
