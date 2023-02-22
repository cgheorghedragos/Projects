const express = require('express');

const collectionService = require('../service/Service');

const collectionRouter = express.Router();

collectionRouter.route('/create-collection').post(createCollection);
collectionRouter.route('/get-nft-collection').get(getNFTes);
collectionRouter.route('/get/:id').get(getNFT);
collectionRouter.route('/update-nft').put(updateNFT);
collectionRouter.route('/delete-nft').delete(deleteNFT);
collectionRouter.route('/save-nft').post(saveNFT);
collectionRouter.route('/get-names').get(getAllNames);
collectionRouter.route('/get-monitoring-collection').get(getTrackedName);
collectionRouter.route('/add-tacked-nft').post(addTrackedCollection);

function createCollection(request, response) {
    const value = request.body;
    collectionService.create(
        value,
        data => {response.status(201).json(data);
                console.log(data)
        },
        error => response.status(400).json(error),
    );
}

function getNFTes(request, response) {
    const value = request.body;

    collectionService.getNFTes(
        data => response.status(200).json(data),
        error => response.status(400).json(error),
    );
}

function getNFT(request, response){
    const value = request.params.id;

    console.log(value);
    collectionService.getById(
        value,
        data => response.status(200).json(data),
        error => response.status(400).json(error),
    );
}

function updateNFT(request, response) {
    const value = request.body;
    const id = value._id;
    value.id = "";

    collectionService.updateNftById(
        id,
        value,
        data => response.status(200).json(data),
        error => response.status(400).json(error),
    );
}

function deleteNFT(request, response) {
    const value = request.body;
    const id = value._id;

    collectionService.deleteNFTbyId(
        id,
        data => response.status(200).json(data),
        error => response.status(400).json(error),
    );
}

function saveNFT(request, response) {
    const value = request.body;

    collectionService.saveCollection(value.name,
        value,
        data => response.status(200).json(data),
        error => response.status(400).json(error),
    );
}

function getAllNames(request, response) {
        
    collectionService.getNFTSavedNames(
        data => response.status(200).json(data),
        error => response.status(400).json(error),
    );
}

function getTrackedName(request, response) {
        
    collectionService.getTrackedCollectionName(
        data => response.status(200).json(data),
        error => response.status(400).json(error),
    );
}

function addTrackedCollection(request, response) {
    const value = request.body;
    collectionService.addTrackedCollectionName(
        value,
        data => response.status(201).json(data),
        error => response.status(400).json(error),
    );
}


module.exports = collectionRouter;