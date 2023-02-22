const cron = require("node-cron");
const mongoose = require('mongoose');
const express = require('express');
const app = express();
const collectionRouter = require('./controller/Controller');
const collectionService = require('./service/Service')
const accountSid = "ACf657291c44510d66cd8da396c914aa88";
const authToken = "67793edc24810ec3370289803f19de00";
const client = require('twilio')(accountSid, authToken);
const myPhoneNumberSender = "+12056795878";
const myPhoneNumberReceiver = "+40736338646";

const sdk = require('api')('@opensea/v1.0#bg4ikl1mk428b');

const startServer = () => {
    app.listen(4000, () => console.log("Server is running"));
}

const startDatabaseConnection = () => {
    mongoose.connect("mongodb://localhost:27017/proiect_scd", () => console.log("Connected to database!"));
}


const fetchData = () => {
     sdk.retrievingCollectionStats({collection_slug: 'doodles-official'})
     .then(res => saveData(res,'doodles-official'))
     .catch(err => console.error(err));

     sdk.retrievingCollectionStats({collection_slug: 'bored-ape-kennel-club'})
     .then(res => saveData(res,'bored-ape-kennel-club'))
     .catch(err => console.error(err));

     sdk.retrievingCollectionStats({collection_slug: 'patchworks'})
     .then(res => saveData(res,'patchworks'))
     .catch(err => console.error(err));

     sdk.retrievingCollectionStats({collection_slug: 'savage-nation'})
     .then(res => saveData(res,'savage-nation'))
     .catch(err => console.error(err));

     sdk.retrievingCollectionStats({collection_slug: 'mutant-hound-collars'})
     .then(res => saveData(res,'mutant-hound-collars'))
     .catch(err => console.error(err));
     
}

const sendSMS = (collectionName, oldPrice, newPrice) => {
    client.messages
          .create({body: collectionName+", noul pret este cu mai mult de 10% mai mic, \npret vechi: "+oldPrice+"\npret nou: "+newPrice, from: myPhoneNumberSender, to: myPhoneNumberReceiver})
          .then(message => console.log(message.sid));
    }

const saveData = (res,collectionName) => {
   
    collectionService.saveCollectionFromOS(collectionName,res,
        data => {},
        error => console.log(error))
}

const checkMonitoringData = () => {
    collectionService.getTrackedCollection(
        data_tracked => {
            sdk.retrievingCollectionStats({collection_slug: data_tracked.name})
                 .then(res => {
      
                    var newPrice = res.stats.floor_price;
                    var oldPrice = Number(data_tracked.floorPrice);
                    if( newPrice <= (oldPrice* 0.90)){
                        sendSMS(data_tracked.name,oldPrice,newPrice);
                    } 
                 })
                 .catch(err => console.error(err));
        }, e => console.log(e)
    )
}


const initRoutes = () => {
    app.use((req, res, next) => {
        res.header('Access-Control-Allow-Origin', '*');
        res.header('Access-Control-Allow-Headers', '*');
        res.header('Access-Control-Allow-Methods', '*');
        next();
    });
    app.use(express.json());
    app.use('/api/collections', collectionRouter);
}

const startApp = () => {
    startServer();
    startDatabaseConnection();
    initRoutes();
}

startApp();

cron.schedule('*/1 * * * *', checkMonitoringData, {});
cron.schedule('*/15 * * * *', fetchData, {});


