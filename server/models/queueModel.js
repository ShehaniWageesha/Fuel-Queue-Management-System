const mongoose = require("mongoose");
const Schema = mongoose.Schema;

let queue = new Schema({
    stationName:{type: String},
    userMobile:{type: Number},
    vehicalType:{type: String},
    fuelType: {type: String},
    joined: {type:Boolean,default:false},
    leaveQueue : {type:String},
},
);

module.exports = mongoose.model("queue", queue);