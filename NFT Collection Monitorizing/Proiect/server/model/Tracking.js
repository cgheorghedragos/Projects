const mongoose = require('mongoose')
const { Schema } = mongoose

const collectionSchema = new Schema({
  name: String,
  date: { type: Date, default: Date.now },
})

const Collection = mongoose.model('Tracking', collectionSchema)

module.exports = Collection