const mongoose = require("mongoose");
const Schema = mongoose.Schema;

let station = new Schema({
    stationName:{type: String},
    stationLocation:{type: String},
    fuelType: {type: String},
    arivalTime: {type : String},
    liters: {type : String},
    finishTime: {type : String}
},
);

module.exports = mongoose.model("station", station); 