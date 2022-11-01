const path = require('path');
const express = require('express');
const Stations = require('../models/stationModel');

const Router = express.Router();

Router.post(
  '/addStation',
    async (req, res) => {
      try {
        let station = new Stations({
          stationName: req.body.stationName,  
          stationLocation: req.body.stationLocation, 
          petrol: req.body.petrol,
          diesel: req.body.diesel,
          parivalTime: req.body.parivalTime,
          darivalTime: req.body.darivalTime, 
          pliters: req.body.pliters,
          dliters: req.body.dliters,
          pfinishTime: req.body.pfinishTime,
          dfinishTime: req.body.dfinishTime,

        });
      await station.save();
      res.send('Satation details uploaded successfully.');
      } catch (error) {
        res.status(400).send('Error while uploading Stations. Try again later.');
      }
    },
    (error, req, res, next) => {
      if (error) {
        res.status(500).send(error.message);
      }
    }
);

Router.post('/add', async (request, response) => {

  const fuelStation = new Stations(request.body);

  console.log(fuelStation);

  fuelStation.save((error, fuelStation) => {
    if (error) {
      response.status(500).json({ error: error.message });
    }
    else {
      response.status(200).
        json({
          success: true,
          fuelStation: fuelStation
        });
    }
  });
}
);
// get station details

Router.get('/getAllStations', async (req, res) => {
  try {
    const stations = await Stations.find({});
    const sortedByCreationDate = stations.sort(
      (a, b) => b.createdAt - a.createdAt
    );
    res.send(sortedByCreationDate);
  } catch (error) {
    res.status(400).send('Error while getting list of Stations. Try again later.');
  }
});


//Update
Router.put("/:id", async (req, res) => {
  try {
    let station = await Stations.findById(req.params.id);
    const data = {
      stationName: req.body.stationName || station.stationName,
      stationLocation: req.body.stationLocation || station.stationLocation,
      petrol: req.body.petrol || station.petrol,
      diesel: req.body.diesel || station.diesel,
      parivalTime: req.body.parivalTime || station.parivalTime,
      darivalTime: req.body.darivalTime || station.darivalTime,
      pliters: req.body.pliters || station.pliters,
      dliters: req.body.dliters || station.dliters,
      pfinishTime: req.body.pfinishTime || station.pfinishTime,
      dfinishTime: req.body.dfinishTime || station.dfinishTime

    };
    station = await Stations.findByIdAndUpdate(req.params.id, data, { new: true });
    res.json(station);
  } catch (e) {
    res.status(400).json({ msg: e.message, success: false });
  }
});

//Delete
Router.delete("/:id", async (req, res) => {
  try {
    // Find station by id
    const station = await Stations.findById(req.params.id);
    if (!station) throw Error('No file found');
    const removed = await station.remove();
    if (!removed)
         throw Error('Something went wrong while trying to delete the file');
    res.json(station);
  } catch (e) {
    res.status(400).json({ msg: e.message, success: false });
  }
});


module.exports = Router;