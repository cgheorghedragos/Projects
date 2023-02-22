const { collection } = require('../model/Model')
const collectionModel = require('../model/Model')
const collectionTracking = require('../model/Tracking')

const collectionService = {

  saveCollection: (collection, success, fail) => {
    collectionModel.create(collection)
      .then(data => success(data))
      .catch(error => fail(error))
  },

  getNFTes: (success, fail) => {
    collectionModel.find()
      .then(data => success(data))
      .catch(error => fail(error))
  },

  getById: (id, success, fail) => {
    collectionModel.findById({ _id: id })
      .then(data => success(data))
      .catch(error => fail(error))
  },

  updateNftById: (id, new_value, success, fail) => {

    collectionModel.findById({ _id: id })
      .then(
        old_value =>
          collectionModel.updateOne(old_value, new_value)
            .then(data => success(data))
            .catch(error => fail(error))
      )
      .catch(error => fail(error));
  },

  deleteNFTbyId: (id, success, fail) => {
    collectionModel.deleteOne({ _id: id })
      .then(data => success(data))
      .catch(error => fail(error))
  },

  saveCollectionFromOS: (name, data, success, fail) => {
    var nft = {};
    var openSeaResponse = data.stats;

    nft.floorPrice = openSeaResponse.floor_price;
    nft.totalSupply = openSeaResponse.total_supply;
    nft.numOwners = openSeaResponse.num_owners;
    nft.name = name;
    nft.totalVolume = openSeaResponse.total_volume;
    nft.sevenDaySales = openSeaResponse.seven_day_sales;

    collectionModel.create(nft)
      .then(data => success(data))
      .catch(error => fail(error))
  },

  getNFTSavedNames: (success, fail) => {
    collectionModel.find()
      .then(nftes => {
        var result = {};
        result.name = [];

        nftes.forEach( nft => {
          var valoare = 0;
          result.name.forEach(nftName => {
            if (nft.name === nftName) {
              valoare = valoare + 1;
            }
          })
          if (valoare == 0) {
            result.name.push(nft.name);
          }
        });
        success(result)
      })
      .catch(error => fail(error))
  },

  addTrackedCollectionName: (data, success, fail) => {
    collectionTracking.create(data)
      .then(data => success(data))
      .catch(error => fail(error))
  },

  getTrackedCollectionName: (success, fail) => {
    collectionTracking.findOne()
      .sort({ date: -1 })
      .limit(1)
      .then(data => success(data))
      .catch(err => fail(err));
  },

  getTrackedCollection: (success, fail) => {
    collectionTracking.findOne()
      .sort({ date: -1 })
      .limit(1)
      .then(data => {
        const name = data.name;
        collectionModel.findOne({ name: name })
          .sort({ date: -1 })
          .then(res => success(res))
          .catch(error => fail(error)
          );
      })
      .catch(err => fail(err));
  }

}

module.exports = collectionService