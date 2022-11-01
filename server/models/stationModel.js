const mongoose = require("mongoose");
const Schema = mongoose.Schema;

let station = new Schema({
    stationName:{type: String},
    stationLocation:{type: String},
    petrol: {type: String},
    diesel: {type: String},
    parivalTime: {type : String},
    darivalTime: {type : String},
    pliters: {type : Number},
    dliters: {type : Number},
    pfinishTime: {type : String},
    dfinishTime: {type : String}

},
);

module.exports = mongoose.model("station", station);