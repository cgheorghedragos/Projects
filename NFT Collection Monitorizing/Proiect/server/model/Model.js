const mongoose = require('mongoose')
const { Schema } = mongoose

const collectionSchema = new Schema({
  floorPrice: String,
  totalSupply: String,
  numOwners: String,
  name: String,
  totalVolume: String,
  sevenDaySale: String,
  date: { type: Date, default: Date.now },
})

const Collection = mongoose.model('Collection', collectionSchema)

module.exports = Collection