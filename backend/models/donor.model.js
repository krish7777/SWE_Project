const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const donorSchema = new Schema({

	name:{type:String,required:true},
	pswd:{type:String,required:true},
	location:{type:String}
})

const donors = mongoose.model('donors',donorSchema);

module.exports = donors;
