const path = require('path');
const express = require('express');
const Queues = require('../models/queueModel');

const Router = express.Router();

Router.post(
  '/addQueue',
    async (req, res) => {
      try {
        let queue = new Queues({
          stationName: req.body.stationName, 
          userMobile: req.body.userMobile,  
          vehicalType: req.body.vehicalType, 
          fuelType: req.body.fuelType,
          joined: req.body.joined ? true : false, 
          leaveQueue: req.body.leaveQueue,
        });
      await queue.save();
      res.send('Queue details uploaded successfully.');
      } catch (error) {
        res.status(400).send('Error while uploading Queue. Try again later.');
      }
    },
    (error, req, res, next) => {
      if (error) {
        res.status(500).send(error.message);
      }
    }
);

// get queue details

Router.get('/getAllQueue', async (req, res) => {
  try {
    const queues = await Queues.find({});
    const sortedByCreationDate = queues.sort(
      (a, b) => b.createdAt - a.createdAt
    );
    res.send(sortedByCreationDate);
  } catch (error) {
    res.status(400).send('Error while getting list of Queues. Try again later.');
  }
});

Router.get('/getAQueue/:stationName', async (req, res) => {
  try {
    const que =await Queues.count({stationName: req.params.stationName, joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
});

Router.get('/getPQueue/:stationName', async (req, res) => {
    try {
    //      const que =await Queues.aggregate( [ { $match: { fuelType: "Petrol", joined: true} },
    //     { $count }
    // ]);
    const que =await Queues.count({stationName: req.params.stationName ,fuelType: "Petrol", joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });

  Router.get('/getCarQueue/:stationName', async (req, res) => {
    try {
    const que =await Queues.count({stationName: req.params.stationName ,vehicalType: "Car", joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });

  Router.get('/getBikeQueue/:stationName', async (req, res) => {
    try {

    const que =await Queues.count({stationName: req.params.stationName ,vehicalType: "Motor Bike", joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });

  Router.get('/getLorryQueue/:stationName', async (req, res) => {
    try {

    const que =await Queues.count({stationName: req.params.stationName ,vehicalType: "Lorry", joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });
  Router.get('/getVanQueue/:stationName', async (req, res) => {
    try {

    const que =await Queues.count({stationName: req.params.stationName ,vehicalType: "Van", joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });

  Router.get('/getDQueue/:stationName', async (req, res) => {
    try {
      const que =await Queues.count({stationName: req.params.stationName ,fuelType: "Diesel", joined: true})
      res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });
  
//Update
Router.put("/:userMobile", async (req, res) => {
  try {
    let queue = await Queues.find({"userMobile" : req.params.userMobile});
    const data = {
      // stationName: req.body.stationName || queue.stationName,
      // userMobile: req.body.userMobile || queue.userMobile,
      // vehicalType: req.body.vehicalType || queue.vehicalType,
      // fuelType: req.body.fuelType || queue.fuelType,
      joined: req.body.joined ? true : false || queue.joined ? true : false,
      leaveQueue: req.body.leaveQueue || queue.leaveQueue,
    };
    queue = await Queues.findOneAndUpdate({"userMobile" :req.params.userMobile}, data, { new: true });
    res.json(queue);
  } catch (e) {
    res.status(400).json({ msg: e.message, success: false });
  }
});


module.exports = Router;